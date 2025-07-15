<%@ page import="mybatis.dao.MemberDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("u_id");

    if(MemberDAO.idCheck(id)) {// boolean 형으로 반환하니까 if(함수) 를 넣어 해당 영역 수행하게 함
%>
    <p id="chk" class="success">사용가능</p>
<%
} else {
%>
    <p id="chk" class="fail">사용불가</p>
<%
    }
%>