<%@ page import="mybatis.vo.EmpVO" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
  Object obj = request.getAttribute("ar_search");
  if(obj != null){
    EmpVO[] ar = (EmpVO[]) obj;
    JSONObject jsonList = new JSONObject();
    JSONArray itemList = new JSONArray();
    int i=0;
    for(EmpVO vo : ar){
      // 자바스크립트에서 인식할 수 있도록 JSON표기법을 구현하자
      JSONObject json = new JSONObject(); // {} 와 같은 형태가 준비됌 (json객체 생성)
      json.put("empno", vo.getEmpno()); // {empno:'1000'}
      json.put("ename", vo.getEname()); // {ename:'이도'}
      json.put("job", vo.getJob()); // {job:'직종'}
      json.put("sal", vo.getSal()); // {sal:'급여'}
      json.put("hiredate", vo.getHiredate());
      json.put("deptno", vo.getDeptno());
      itemList.put(json); // [ { 키1: 값1, 키2: 값2, 키3: 값3} ] 저장된 키와 값들이 저장되어 [ ] 에 담김
    } // for end
    jsonList.put("items", itemList); // { items:[ { 키1: 값1, 키2: 값2, 키3: 값3} ] } 같은 형태 준비됌
    out.println(jsonList); // 응답내용
  }
  // http://localhost:8080/Controller?type=search2&searchType=1&searchValue=m
%>