<%@ page import="mybatis.dao.MemberDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("u_id");
    boolean res = MemberDAO.idCheck(id);
    int chk = 0;
    if(res)
        chk = 1;

    /*
    if(MemberDAO.idCheck(id)) // boolean 형으로 반환하니까 if(함수) 를 넣어 해당 영역 수행하게 함
        response.sendRedirect("reg.jsp?m_id="+id+"&chk=1");
    else
        response.sendRedirect("reg.jsp?m_id="+id+"&chk=0");
     */
%>
<jsp:forward page="reg.jsp">
    <jsp:param name="chk" value="<%=chk%>"/>
</jsp:forward>