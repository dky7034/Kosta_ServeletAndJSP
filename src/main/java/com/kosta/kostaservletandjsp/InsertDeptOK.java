package com.kosta.kostaservletandjsp;

// 필요한 라이브러리와 패키지를 임포트
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

// 서블릿 매핑 설정: "/InsertDeptOK" URL로 요청이 들어오면 이 서블릿이 처리함
@WebServlet("/InsertDeptOK")
public class InsertDeptOK extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청의 문자 인코딩을 UTF-8로 설정
        request.setCharacterEncoding("UTF-8");

        // 요청으로부터 부서 번호, 이름, 위치를 파라미터로 받아옴
        int dno = Integer.parseInt(request.getParameter("dno"));
        String dname = request.getParameter("dname");
        String dloc = request.getParameter("dloc");

        // 데이터베이스에 삽입할 SQL 문
        String sql = "INSERT INTO DEPT VALUES(?,?,?)";

        // SQL 실행 결과를 저장할 변수 (초기값 -1)
        int re = -1;

        try {
            // JDBC 드라이버 클래스 이름
            String driver = "oracle.jdbc.driver.OracleDriver";
            // 데이터베이스 연결 URL
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            // 데이터베이스 사용자 이름
            String username = "c##madang";
            // 데이터베이스 비밀번호
            String password = "madang";

            // JDBC 드라이버 로드
            Class.forName(driver);
            // 데이터베이스 연결 생성
            Connection conn = DriverManager.getConnection(url, username, password);
            // PreparedStatement 객체 생성 및 SQL 문 설정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // SQL 문에 파라미터 설정
            pstmt.setInt(1, dno);
            pstmt.setString(2, dname);
            pstmt.setString(3, dloc);

            // SQL 문 실행 및 결과 저장
            // SQL 문을 실행하면 executeUpdate() 메서드는
            // 해당 SQL 문에 의해 삽입 or 수정 or 삭제된 행의 수를 반환합니다.
            // re 값이 1 이상이면: SQL 문이 성공적으로 실행되었으며, re 값은 삽입된 행의 수를 나타냅니다.
            // 예를 들어, INSERT INTO DEPT VALUES(?,?,?) SQL 문이 한 개의 행을 삽입했다면, re 값은 1이 됩니다.
            // re 값이 0이면: SQL 문이 실행되었으나, 어떠한 행도 영향을 받지 않았음을 나타냅니다.
            // INSERT 문에서는 보통 이 경우가 발생하지 않지만, UPDATE 또는 DELETE 문에서는 조건에 맞는 행이 없는 경우 0을 반환할 수 있습니다.
            re = pstmt.executeUpdate();

            // 자원 해제
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            // 예외 발생 시 메시지 출력
            System.out.println("예외 발생:" + e.getMessage());
        }

        // 클라이언트에게 응답을 보내기 위해 PrintWriter 객체 생성
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // SQL 실행 결과에 따라 성공 또는 실패 메시지 출력
        if (re > 0) {
            out.print("부서 등록 성공");
        } else {
            out.print("부서 등록 실패");
        }
        // PrintWriter 객체 닫기
        out.close();
    }
}
