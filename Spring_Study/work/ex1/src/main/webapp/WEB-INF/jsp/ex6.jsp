<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>회원목록</h1>
    <hr/>
    <ul>
        <c:forEach var="vo" items="${ar}">
            <li>${vo.name} / ${vo.phone}</li>
        </c:forEach>
    </ul>
</body>
</html>
