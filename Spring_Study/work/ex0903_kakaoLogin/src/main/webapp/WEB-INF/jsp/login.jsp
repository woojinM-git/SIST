<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
    <header>
        <h1>로그인</h1>
    </header>
    <article>
        <%-- 세션에 "mvo"라는 이름으로 값이 없다면 로그인 화면을 보여줘야함 --%>
        <c:if test="${sessionScope.mvo eq null}">
            <form method="post" action="">
                ID : <input type="text" name="m_id"/><br/>
                PW : <input type="password" name="m_pw"/><br/>
                <button type="button">LOGIN</button>
            </form>
            <br/>
            <a href="https://kauth.kakao.com/oauth/authorize?client_id=43f7c1d844ead9bd00206b66febae97a&redirect_uri=http://localhost:8080/login/kakao&response_type=code">
                <img src="resources/images/kakao_login.png"/>
            </a>
        </c:if>
    </article>
</body>
</html>
