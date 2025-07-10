package com.example.ex_0709;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MyServlet6")
public class MyServlet6 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청시 한글처리
        request.setCharacterEncoding("UTF-8");
        // 응답을 위한 한글처리
        response.setContentType("text/html; charset=UTF-8");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String[] phone = request.getParameterValues("phone");
        String[] hobby = request.getParameterValues("chk");

        // 응답을 위해 스트림 생성
        PrintWriter out = response.getWriter();

        // 응답 시작
        out.println("<h2>받은 값</h2>");
        out.println("<p>아이디:"+id+"</p>");
        out.println("<p>이름:"+name+"</p>");
        out.println("<p>연락처:");
        for(int i=0; i<phone.length; i++){
            out.println(phone[i]);
            if(i < phone.length-1){ // i가 2보다 작으면 "-"출력
                out.println("-");
            }
        }
        out.print("</p>");
        out.println("<p>취미:");
        for(int i=0; i<hobby.length; i++){
            out.println(hobby[i]);
            if(i < hobby.length-1){ // i가 2보다 작으면 "-"출력
                out.println(", ");
            }
        }
        out.print("</p");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
