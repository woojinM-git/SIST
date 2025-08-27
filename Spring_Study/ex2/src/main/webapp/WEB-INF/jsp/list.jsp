<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>사원목록</h1>
    <hr/>
    <ul>
        <c:forEach items="${ar}" var="vo">
            <li>${vo.empno} / ${vo.ename}</li>
        </c:forEach>
    </ul>
</body>
</html>
