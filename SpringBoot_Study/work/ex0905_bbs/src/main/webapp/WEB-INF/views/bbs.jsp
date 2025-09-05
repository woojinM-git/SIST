<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <style>
        table{
            width: 500px;
            border-collapse: collapse;
        }
        table th, table td{
            border: 1px solid black;
            padding: 5px;
        }
        table caption{
            text-indent: -9999px;
            height: 0;
        }
        .no-border{ border: none; }
    </style>
</head>
<body>
<div id="wrap">
    <header>
        <h1>부서목록</h1>
    </header>
    <article>
            <table summary="게시판 목록" id="t1">
            <caption>게시판 목록</caption>
            <thead>
            <tr>
                <td colspan="5">
                    <form action="search" method="post">
                        <select name="searchType">
                            <option value="0">제목</option>
                            <option value="1">내용</option>
                        </select>
                        <input type="text" name="searchValue"/>
                        <button type="button" onclick="exe(this.form)">검색</button>
                    </form>
                </td>
            </tr>
            <tr class="title">
                <th class="no">번호</th>
                <th class="subject">제목</th>
                <th class="writer">글쓴이</th>
                <th class="reg">날짜</th>
                <th class="hit">조회수</th>
            </tr>
            </thead>

        <tbody>
        <c:forEach var="vo" items="${ar}" varStatus="i">
            <tr>
                <td>${vo.b_idx}</td>
                <td style="text-align: left">
                    <a href="view?bname=BBS&b_idx=${vo.b_idx}&cPage=">
                            ${vo.subject}
                    </a></td>
                <td>${vo.writer}</td>
                <td>${vo.write_date}</td>
                <td>${vo.hit}</td>
            </tr>
        </c:forEach>
        </tbody>
        </table>
    </article>
</div>

<script>

    function exe(frm){
        let v = frm.searchValue.value;
        if(v.trim().length < 1){
            alert("검색어를 입력하세요");
            frm.searchValue.focus();
            return;
        }
        frm.submit();
    }
</script>
</body>
</html>
