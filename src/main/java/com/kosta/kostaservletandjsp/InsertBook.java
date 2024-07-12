package com.kosta.kostaservletandjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/InsertBook")
public class InsertBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = "";
        data += "<h2>도서등록</h2>";
        data += "<hr>";
        data += "<form action='InsertBookOK' method='post'>";
        data += "도서번호:";
        data += "<input type='text' name='bookid'><br>";
        data += "도서명:";
        data += "<input type='text' name='bookname'><br>";
        data += "가격:";
        data += "<input type='text' name='price'><br>";
        data += "출판사:";
        data += "<input type='text' name='publisher'><br>";
        data += "<input type='submit' value='등록'>";
        data += "<input type='reset' value='취소'>";
        data += "</form>";

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); // 스트림을 얻어옴
        out.print(data); // 스트림을 통해 출력
        out.close(); // 사용한 스트림을 닫아줌

    }
}
