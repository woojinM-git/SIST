package com.example.ex_0709;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MyServlet4")
public class MyServlet4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String[] phone = request.getParameterValues("phone");
        String book = request.getParameter("book");
        String movie = request.getParameter("movie");
        String game = request.getParameter("game");
        
        // 응답을 위한 한글처리
        response.setContentType("text/html; charset=UTF-8");
        // 응답을 위한 스트림 생성
        PrintWriter out = response.getWriter();
        // 응답 시작
        out.println("<h2>받은 값:</h2>");
        out.println("<ul>");

        out.println("<li>id: " + id + "</li>");
        out.println("<li>name: " + name + "</li>");
        out.print("<li>\r" + "phone: ");
        for(int i = 0; i<phone.length; i++){
            out.println(phone[i]);
            if(i < phone.length-1) // i 가 2보다 작으면 "-" 출력하자
                out.print("-");
        }
        out.println("</li>");

        if(!(book == null))
            out.println("<li>book</li>");
        if(!(movie == null))
            out.println("<li>movie</li>");
        if(!(game == null))
            out.println("<li>game</li>");

        out.println("</ul>");
        out.close();

    }
}
