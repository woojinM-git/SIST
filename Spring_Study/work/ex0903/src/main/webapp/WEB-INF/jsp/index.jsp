<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        table{
            width: 600px;
            border-collapse: collapse;
        }
        table th, table td {
            border: 1px solid black;
            padding: 5px;
        }
        table caption {
            text-indent: -9999px;
            height: 0;
        }
    </style>
</head>
<body>
<header>
    <h1>회원목록</h1>
</header>
<article>

    <form action="search" method="post">
        <select id="type">
            <c:forEach var="vo" items="${requestScope.ar}">
                <option value="${vo}">${vo}</option>
            </c:forEach>
        </select>
        <input type="text" id="value" class="w150"/>
        <button type="button" id="btn1">검색</button>
    </form>

    <table>
        <caption>행사목록테이블</caption>
        <thead>
        <tr>
            <th>번호</th>
            <th>이미지</th>
            <th>제목</th>
            <th>전화번호</th>
            <th>주소</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vo" items="${requestScope.ar}" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td><img src="${vo.firstimage2}"/></td>
                <td>${vo.title}</td>
                <td>${vo.tel}</td>
                <td>${vo.addr1} &nbsp; ${vo.addr2}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</article>
</body>
</html>
