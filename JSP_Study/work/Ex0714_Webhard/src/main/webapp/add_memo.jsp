<%@ page import="mybatis.dao.MemoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  System.out.println("test1");
  // 요청시 한글처리
  request.setCharacterEncoding("utf-8");

  // 파라미터 값 받기
  String writer = request.getParameter("writer");
  String content = request.getParameter("content");
  String ip = request.getRemoteAddr(); // 접속자 ip

  int cnt = MemoDAO.addMemo(writer, content, ip);


  // 강제 페이지 이동
  response.sendRedirect("memoList.jsp?cmd="+cnt); // 전 화면 불러오기 (로딩 새로고침) // Redirect는 절대로 get 방식임
  // ? 를 기준으로 왼쪽이 경로 오른쪽이 인자 이다.
%>