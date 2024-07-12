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
import java.sql.PreparedStatement;

@WebServlet("/InsertBookOK")
public class InsertBookOK extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼 양식에서 새로운 도서 등록을 위하여 입력한 내용을 가지고 doPost로 옵니다.
        // 이 때, 사용자의 요청정보는 request에 실려서 옵니다.
        // request를 통해서 사용자가 요청한 정보(도서번호, 도서명, 출판사, 가격)를 받아올 수 있습니다.
        // 받아오기 전에 요청한 문자셋이 한글임을 먼저 설정한 후 데이터를 받아와야 합니다.

        // 요청한 문자셋이 한글임을 설정합니다.
        request.setCharacterEncoding("UTF-8");

        // 새로운 도서 등록을 위하여 요청한 데이터(도서번호, 도서명, 출판사, 가격)를 받아와서 변수에 저장합니다.
        // 요청한 데이터는 모두 doPost 메서드의 매개변수인 request에 실려 있습니다.
        int bookid = Integer.parseInt(request.getParameter("bookid"));
        int price = Integer.parseInt(request.getParameter("price"));
        String publisher = request.getParameter("publisher");
        String bookname = request.getParameter("bookname");

        // 데이터베이스에 연결하여 실행할 데이터베이스 명령어를 만듭니다.
        String sql = "INSERT INTO BOOK VALUES(?, ?, ?, ?)";

        int re = 0; // Execute update result

        // 데이터베이스 연결을 위한 드라이버 이름, url, username, password 변수를 만듭니다.
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "c##madang";
            String password = "madang";

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookid);
            pstmt.setInt(2, price);
            pstmt.setString(3, publisher);
            pstmt.setString(4, bookname);

            re = pstmt.executeUpdate();

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        // PrintWriter를 사용하여 클라이언트에게 응답을 보냅니다.
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (re > 0) {
            out.print("도서 등록 성공");
        } else {
            out.print("도서 등록 실패");
        }
        out.close();
    }
}
