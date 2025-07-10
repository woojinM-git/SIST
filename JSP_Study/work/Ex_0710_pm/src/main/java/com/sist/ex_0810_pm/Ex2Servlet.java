package com.sist.ex_0810_pm;

import mybatis.dao.EmpDAO;
import mybatis.vo.EmpVO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Ex2")
public class Ex2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청시 한글처리
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 파라미터 값 받기
        String empno = request.getParameter("empno_tx");
        EmpVO vo = EmpDAO.getEmp(empno);

        StringBuffer sb = new StringBuffer();
        if (vo != null) {
            sb.append("<p>");
            sb.append(vo.getEmpno()); // 사번
            sb.append(", ");
            sb.append(vo.getEname()); // 이름
            sb.append(", ");
            sb.append(vo.getJob()); // 직종
            sb.append(", ");
            sb.append(vo.getDeptno()); // 부서코드
            sb.append("</p>");
        }

        PrintWriter out = response.getWriter();
        out.println("<h2>검색결과</h2>");
        out.println("<hr/>"); // 줄긋기
        out.println(sb.toString());
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
