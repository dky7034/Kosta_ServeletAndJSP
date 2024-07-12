<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %><%--
  Created by IntelliJ IDEA.
  User: donggyun
  Date: 2024. 7. 12.
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    // 새로운 도서 등록을 위하여 사용자가 입력한 도서의 정보(도서번호, 도서명, 가격, 출판사)는
    // jsp 내장 객체인 request에 실려서 옵니다.

    // 사용자가 요청한 문자셋은 한글임을 설정
    request.setCharacterEncoding("UTF-8");

    // 사용자가 입력한 도서번호, 도서명, 가격, 출판사를 받아와서 변수에 저장
    int bookid = Integer.parseInt(request.getParameter("bookid"));
    String bookname = request.getParameter("bookname");
    int price = Integer.parseInt(request.getParameter("price"));
    String publisher = request.getParameter("publisher");

    // 데이터베이스에 연결하여 실행할 데이터베이스 명령어 sql을 만듭니다.
    String sql = "INSERT INTO BOOK(BOOKID, BOOKNAME, PRICE, PUBLISHER) VALUES(?, ?, ?, ?)";
    // 데이터베이스 연결에 필요한 변수를 만듭니다.
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String username = "c##madang";
    String password = "madang";
    // try-catch 밖에서도 변수 re를 사용하기 위해:
    int re = 0;
    try {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, bookid);
        pstmt.setString(2, bookname);
        pstmt.setInt(3, price);
        pstmt.setString(4, publisher);
        re = pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    } catch (Exception e) {
        System.out.println("예외 발생: " + e.getMessage());
        out.print("예외 발생: " + e.getMessage());
    }
    if (re > 0) {
        %>
            <h2>도서 등록 성공</h2>
        <%
    } else {
        %>
            <h2>도서 등록 실패</h2>
        <%
    }

%>
</body>
</html>
