<%@ page import="static java.lang.Integer.parseInt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String num = request.getParameter("num");
  int cnt = Integer.parseInt(num);
%>
<h4>입력값:<%=cnt%></h4>
<%
    StringBuffer sb = new StringBuffer();
    for(int i=1; i<10; i++){
        sb.append(num);
        sb.append("*");
        sb.append(i);
        sb.append("=");
        sb.append(cnt*i);
        sb.append("<br>");
    } // for end
%>
    <%=sb.toString()%>
</body>
</html>
