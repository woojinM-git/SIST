
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

<c:set var="변수명" value="${requestScope.page}" scope="page"/>

<li class="now">${i.index}</li> 사용할 때

${fn: 함수}

스크린 이미지
https://www.megabox.co.kr/static/pc/images/reserve/img-theater-screen.png