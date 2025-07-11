<%@ page import="java.time.LocalDate" %><%-- 한글처리 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%-- @가 들어가면 지시자 --%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>JSP 첫 예제</h2>
    <hr/>
    <h3>1. 스크립트 요소</h3>
    <ol>
        <li>선언문:</li>
        <li>출력문:</li>
        <li>스크립트릿:</li>
    </ol>
    <p>스크립트요소는 각 문들이 겹쳐서 사용할 수 없다.</p>
    <%! // 선언문 (JAVA 영역) java 문법 사용가능
        String msg = "쌍용교육센터"; // 멤버변수 (멤버변수인데 왜 반환함?)
        int value = 100000;

        public String test() {
            LocalDate now = LocalDate.now();
            return msg + ":" + now.toString();
        }
    %>
<h1><%=msg%>,<%=value%></h1>
<h1><%=test()%></h1>
</body>
</html>
