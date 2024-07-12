package com.kosta.kostaservletandjsp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/ListCustomer")
public class ListCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "SELECT CUSTID, NAME FROM CUSTOMER";
        String data = "<html>";
        data += "<body>";
        data += "<h2>고객 목록</h2>";
        data += "<hr>";
        data += "<ul>";

        // 데이터베이스 연결 코드:
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "c##madang";
        String password = "madang";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int custid = rs.getInt(1);
                String name = rs.getString(2);
                data += "<li><a href='DetailCustomer?custid="+custid+"'>"+name+"</a></li>"; // 동적으로 노드 생성(li 생성)
            }
            // 사용했던 자원들 반환
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        data += "</ul>";
        data += "</body>";
        data += "</html>";

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(data);
        out.close();
    }

}
