package com.sist.ex_0810_pm;

import mybatis.dao.EmpDAO;
import mybatis.vo.MemVO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/Ex5")
public class Ex5Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mid = request.getParameter("id_tx");
        String mpw = request.getParameter("pw_tx");

        MemVO mvo = EmpDAO.login(mid, mpw); // 둘 중 하나라도 값이 들어오지 않는다면 null이 반환
        if(mvo != null) {
            // 세션을 얻어낸다
            HttpSession session = request.getSession();
            session.setAttribute("mvo", mvo);
            // 페이지 이동
            response.sendRedirect("/ex1_emp.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    /*
      $("#login_btn").click(function (){ // 로그인 버튼
            $("#login_dig").dialog("open");
        });

        $("#login_dig").dialog(option); // 다이얼로그창 등록

        $("#send_login").click(function (){ // 로그인 버튼
            // 유효성 검사 생략
            document.frm2.submit();
        });
     */
}
