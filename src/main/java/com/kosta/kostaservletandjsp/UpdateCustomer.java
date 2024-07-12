package com.kosta.kostaservletandjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int custid = Integer.parseInt(request.getParameter("custid"));
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "c##madang";
        String password = "madang";

        String sql = "SELECT * FROM CUSTOMER WHERE CUSTID = ?";
        // try-catch 밖에서 사용하기 위한 변수 선언
        String name = "";
        String address = "";
        String phone = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, custid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                name = rs.getString(2);
                address = rs.getString(3);
                phone = rs.getString(4);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        String data = "";
        data += "<html>";
        data += "<body>";
        data += "<h2>고객수정</h2>";
        data += "<hr>";
        data += "<form action='UpdateCustomer' method='post'>";
        data += "고객번호: <input type='text' name='custid' value='"+custid+"'><br>"; // hidden:
        data += "고객이름: <input type='text' name='name' value='"+name+"'><br>";
        data += "고객주소: <input type='text' name='address' value='"+address+"'><br>";
        data += "고객전화: <input type='text' name='phone' value='"+phone+"'><br>";
        data += "<input type='submit' value='수정'>";
        data += "<input type='reset' value='다시입력'>";
        data += "</form>";
        data += "</body>";
        data += "</html>";

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(data);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int custid = Integer.parseInt(request.getParameter("custid"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        String sql = "UPDATE CUSTOMER SET NAME = ?, ADDRESS = ?, PHONE = ? WHERE CUSTID = ?";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "c##madang";
        String password = "madang";
        int re = -1;
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, phone);
            pstmt.setInt(4, custid);
            re = pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (re > 0) {
            out.print("고객 등록 성공");
        } else {
            out.print("고객 등록 실패");
        }
    }
}
