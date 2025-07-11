<%@ page import="mybatis.dao.MemoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<%
  System.out.println("test1");
  // 요청시 한글처리
  request.setCharacterEncoding("utf-8");

  // 파라미터 값 받기
  String writer = request.getParameter("writer");
  String content = request.getParameter("content");
  String ip = request.getRemoteAddr(); // 접속자 ip

  int cnt = MemoDAO.addMemo(writer, content, ip);
  response.sendRedirect("memoList.jsp");
%>

<script>
  <% if(cnt > 0){
  System.out.println("test3");%>
  alert(" 저장 완료 ");
  <% } else { %>
  alert(" 저장 실패 ");
  <% } %>
</script>
</body>
</html>
