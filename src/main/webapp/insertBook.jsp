<%--
  Created by IntelliJ IDEA.
  User: donggyun
  Date: 2024. 7. 12.
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>도서등록</h2>
    <hr>
    <form action="insertBookOK.jsp" method="post">
        도서번호: <input type="text" name="bookid"><br>
        도서가격: <input type="text" name="price"><br>
        출판사명: <input type="text" name="publisher"><br>
        도서이름: <input type="text" name="bookname"><br>
        <input type="submit" value="등록">
        <input type="reset" value="다시입력">
    </form>
</body>
</html>
