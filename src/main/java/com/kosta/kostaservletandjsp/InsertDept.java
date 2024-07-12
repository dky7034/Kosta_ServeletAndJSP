package com.kosta.kostaservletandjsp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/InsertDept")
public class InsertDept extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = "";
        data += "<h2>부서등록</h2>";
        data += "<hr>";
        data += "<form action='InsertDeptOK' method='post'>";
        data += "부서번호:";
        data += "<input type='text' name='dno'><br>";
        data += "부서명:";
        data += "<input type='text' name='dname'><br>";
        data += "부서위치:";
        data += "<input type='text' name='dloc'><br>";
        data += "<input type='submit' value='등록'>";
        data += "<input type='reset' value='취소'>";
        data += "</form>";

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter(); // 스트림을 얻어옴
        out.print(data); // 스트림을 통해 출력
        out.close(); // 스트림을 닫아줌
    }

}
