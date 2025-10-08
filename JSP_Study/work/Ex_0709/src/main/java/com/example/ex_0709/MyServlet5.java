package com.example.ex_0709;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MyServlet5")
public class MyServlet5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청시 (request) 한글 처리
        request.setCharacterEncoding("UTF-8");
        // 응답을 위한 (response) 한글 처리
        response.setContentType("text/html; charset=UTF-8");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String[] phone = request.getParameterValues("phone");
        String[] hobby = request.getParameterValues("chk");

        PrintWriter out = response.getWriter();

        // 응답 시작
        out.println("<ul>");

        out.println("<li>");
        out.println("ID : "+id);
        out.println("</li>");

        out.println("<li>");
        out.println("Name : "+name);
        out.println("</li>");

        out.println("<li>");
        out.println("Phone : "+phone[0]);
        out.println("-");
        out.println(phone[1]);
        out.println("-");
        out.println(phone[2]);
        out.println("</li>");

        out.println("<li>");
        out.println("Hobby : ");
        for (int i=0; i<hobby.length; i++){
            out.println(hobby[i]);
        } // 반복문 끝
        out.println("</li>");

        out.println("</ul>");
        out.close();
    }
}