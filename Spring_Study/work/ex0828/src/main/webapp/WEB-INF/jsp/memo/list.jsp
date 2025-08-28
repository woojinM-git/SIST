<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>메모 목록</h1>
    <hr/>
    <ul>
        <c:forEach items="${ar}" var="vo">
            <li>${vo.idx} / ${vo.writer} / ${vo.content} / ${vo.reg_date}</li>
        </c:forEach>
    </ul>
</body>
</html>
