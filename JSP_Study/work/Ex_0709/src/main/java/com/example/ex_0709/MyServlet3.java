package com.example.ex_0709;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MyServlet3")
public class MyServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ------------------------가장 즁요 ---------------------------
        // 요청시 한글처리
        request.setCharacterEncoding("UTF-8");
        
        // 파라미터들 받기 - 파라미터 이름이 같은 것들은 배열로 받아야 한다.
        String[] names = request.getParameterValues("m_name");
        // ------------------------------------------------------------

        // 응답을 위한 한글처리
        response.setContentType("text/html; charset=UTF-8");
        // 응답을 위한 스트림 생성
        PrintWriter out = response.getWriter();
        // 응답 시작
        out.println("<h2>받은 값:</h2>");
        out.println("<ul>");
        for(int i = 0; i<names.length; i++){
            // 클라이언트에서 서버로 값을 보낼 때는 비어있더라도 공백을 무조건 보낸다.
            // 공백을 제거한 값이 담긴 배열의 길이만큼 반복문을 돌려 값을 받아 응답하자

            out.print("<li>\r");
            out.println(names[i]);
            out.println("</li>");
        } // for end
        out.println("</ul>");
        out.close();
    }
}
