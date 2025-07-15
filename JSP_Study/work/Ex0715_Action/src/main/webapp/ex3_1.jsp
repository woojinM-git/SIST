<%@ page import="test.vo.ParamVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<%
    // 요청시 한글처리
    request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="pvo" class="test.vo.ParamVO" scope="session"/>
<%--  ParamVO pvo = new ParamVO(); 와 같은 의미 --%>
<jsp:setProperty name="pvo" property="*"/>

    <h3><%=pvo.getS_name()%></h3>
    <h3><%=pvo.getS_age()%></h3>
    <h3><%=pvo.getS_email()%></h3>
    <button type="button" onclick="javascript:location.href='ex3_2.jsp'">다음페이지</button>
</body>
</html>
