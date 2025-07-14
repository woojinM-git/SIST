<%@ page import="mybatis.dao.MemberDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    System.out.println("test1");
    // 요청시 한글처리
    request.setCharacterEncoding("utf-8");

    String u_id = request.getParameter("u_id");

    int cnt = MemberDAO.usechk(u_id);

    response.sendRedirect("reg.jsp?cnt="+cnt);
%>