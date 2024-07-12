package com.kosta.kostaservletandjsp;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = "<h1>Hello World</h1>";
        data += "<hr>";
        data += "<h1>안녕 서블릿</h1>";
        // 응답방식을 설정
        response.setContentType("text/html;charset=utf-8");
        // 동적으로 생성한 html을 요청한 사용자의 브라우저에 응답(출력)하기 위하여 스트림을 얻어옴
        PrintWriter out = response.getWriter();
        // 출력스트림 out을 통해서 html을 응답(출력)
        out.print(data);
        // 스트림을 닫아줌
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
