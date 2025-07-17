<%@ page import="mybatis.vo.EmpVO" %>
<%@ page import="mybatis.vo.DeptVO" %>
<%@ page import="mybatis.dao.MemoDAO" %>
<%@ page import="mybatis.vo.MemoVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <style>
    #table{
      width: 600px;
      border-collapse: collapse;
    }
    #table th, #table td{
      border: 1px solid black;
      padding: 5px;
    }
    #table caption{
      text-indent: -999999px; /* 화면상에서 안보이게 함 */
    }
  </style>
</head>
<body>
<div id="wrap">
  <header>
    <h2>메모목록</h2>
  </header>
  <article>
    <table id="table">
      <caption>메모목록 테이블</caption>
      <thead>
      <tr>
        <th>작성자</th>
        <th>내용</th>
        <th>작성일</th>
      </tr>
      </thead>
      <tbody>
      <%
        // request에 emp라는 이름으로 저장된 객체를 가져온다.
        Object obj = request.getAttribute("memo");

        MemoVO[] ar = null;
        if(obj != null){
          ar = (MemoVO[]) obj;
          for(MemoVO vo : ar){
      %>
      <tr>
        <td><%=vo.getWriter()%></td>
        <td><%=vo.getContent()%></td>
        <td><%=vo.getReg_date()%></td>
      </tr>
      <%
          }
        }
      %>
      </tbody>
    </table>
  </article>
</div>
</body>
</html>
