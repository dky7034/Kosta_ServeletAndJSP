package com.kosta.kostaservletandjsp;

// 필요한 라이브러리와 패키지를 임포트
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

// 서블릿 매핑 설정: "/DetailCustomer" URL로 요청이 들어오면 이 서블릿이 처리함
@WebServlet("/DetailCustomer")
public class DetailCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청으로부터 고객 번호를 파라미터로 받아옴
        int custid = Integer.parseInt(request.getParameter("custid"));
        System.out.println("고객번호: " + custid); // 고객 번호 출력 (디버깅 용도)

        // 데이터베이스 조회를 위한 SQL 문
        String sql = "SELECT * FROM CUSTOMER WHERE CUSTID = ?";

        // 응답(출력)을 위한 응답 방식 설정
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); // 출력을 위한 스트림 생성
        String data = "<html>"; // HTML 시작 태그
        data += "<body>"; // BODY 시작 태그

        // 데이터베이스 연결 정보 설정
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "c##madang";
        String password = "madang";
        try {
            // JDBC 드라이버 로드
            Class.forName(driver);
            // 데이터베이스 연결 생성
            Connection conn = DriverManager.getConnection(url, username, password);
            // PreparedStatement 객체 생성 및 SQL 문 설정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // SQL 문에 파라미터 설정
            pstmt.setInt(1, custid);
            // SQL 문 실행 및 결과 집합(ResultSet) 반환
            ResultSet rs = pstmt.executeQuery();
            // 조회할 레코드가 한 개 밖에 없을 때에는 while(rs.next()) 보다는 if(rs.next())를 사용합니다.
            if (rs.next()) {
                // 결과 집합으로부터 데이터 추출
                String name = rs.getString(2);
                String address = rs.getString(3);
                String phone = rs.getString(4);

                // 추출한 데이터를 HTML 형식으로 추가
                data += "고객번호: " + custid + "<br>";
                data += "고객이름: " + name + "<br>";
                data += "고객주소: " + address + "<br>";
                data += "고객전화: " + phone + "<br>";
            }
            // ResultSet 닫기
            rs.close();
            // PreparedStatement 닫기
            pstmt.close();
            // Connection 닫기
            conn.close();
        } catch (Exception e) {
            // 예외 발생 시 메시지 출력
            System.out.println("예외 발생: " + e.getMessage());
        }
        data += "<hr>"; // 구분선 추가
        data += "<a href='UpdateCustomer?custid="+custid+"'>수정</a>";
        data += "<br>";
        data += "<a href='DeleteCustomer?custid="+custid+"'>삭제</a>";

        data += "</body>"; // BODY 종료 태그
        data += "</html>"; // HTML 종료 태그
        out.print(data); // 클라이언트에 HTML 응답 전송
        out.close(); // 사용이 끝난 스트림 닫기
    }
}
