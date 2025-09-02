<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 참조할 페이지들 선언 --%>
<c:import url="nav_head.jsp" var="nav_head"/>
<c:import url="nav_foot.jsp" var="nav_foot"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- 외부 CSS 연결하기 -->
    <link rel="stylesheet" href="/resources/css/common.css">
    <link rel="stylesheet" href="/resources/css/sub_tab.css">
</head>
<body>
<article id="wrap">
    <!--=================== 상단영역 =================-->
    ${nav_head}
    <!--================= 상단영역 끝 =================-->
    <!--================== 콘텐츠영역 =================-->
    <div id="contents">
        <p>
            <img src="/resources/img/@img05.png" alt="이미지5"/>
        </p>
        <div class="tab_type01">
            <ul>
                <li id="t1"><a href="javascript:ex1()">위드유</a></li>
                <li id="t2" class="selected"><a href="javascript:ex2()">위드유 영상</a></li>
                <li id="t3"><a href="javascript:ex3()">위드유 대화</a></li>
            </ul>
        </div>

        <!-- 각 탭에 표현할 내용들 -->
        <div id="tab1" class="tab_cont">
            위드유 내용 입니다.
        </div>
        <div id="tab2" class="tab_cont show">
            [OSEN=고용준 기자] 생각 밖의 접전이었고, 힘든 승리였음에도 여유가 있었다. ‘페이커’ 이상혁은 CTBC 플라잉 오이스터(CFO) 미드 라이너 ‘홍큐’ 차이밍홍의 경기력을 인정하면서 다음 상대인 LPL 강호 빌리빌리 게이밍(BLG)전의 필승을 다짐했다.
            T1은 4일 오전 (이하 한국시간)  캐나다 밴쿠버 퍼시픽 콜리세움에서 열린 ‘2025 미드 시즌 인비테이셔널(이하 MSI)’ 브래킷 스테이지 1라운드 CTBC 플라잉 오이스터(CFO)와 경기에서 1세트 승리 이후 2, 3세트를 패하며 매치포인트를 허용했지만, 4, 5세트를 잡아내면서 힘겨운 3-2 역전승을 거뒀다.
            이로써 T1은 승자조 2라운드에 진출해 모비스타 코이(MKOI) 꺾은 빌리빌리 게이밍(BLG)와 3라운드 진출을 다투게 됐다.
            경기 후 무대 인터뷰에 ‘구마유시’ 이민형과 함께 참가한 ‘페이커’ 이상혁은 풀세트 접전의 승리 요인으로 미드 공략을 꼽았다. 그의 말대로 T1은 5세트 초반 미드를 연달아 공략하면서 스노우볼의 발판을 마련했다.
            “(5세트의 경우)미드에서의 플레이가 중요하다고 생각한다. 초반 우리가 좋지 못했지만, 중반 이후 준비한대로 경기를 풀어 나갈 수 있었다. 그래도 생각했던 것보다 상대인 ‘홍큐’ 선수가 굉장히 잘했다. 그래서 어려운 경기였는데 오늘 승리해 기쁘다.”
            캐나다 현지에서 북미 솔로랭크에 대한 질문에 그는 “항상 솔로랭크는 도움이 된다. 비록 핑이 좀 높기는 하지만 재밌게 하고 있다. 북미 서버 플레이어들은 굉장히 채팅이 많아 그걸 보는 재미가 있다”라고 환하게 웃었다.
            T1의 다음 상대는 LPL의 강호 BLG. ‘페이커’ 이상혁은 첫 상대였던 CFO전 만큼 어렵지 않은 경기를 낙관했다.
            “BLG와 또 만나게 될 줄 몰랐는데 이렇게 만나게 돼 너무 재밌을 것 같다. 오늘 만큼 힘든 경기는 안 나올 거라고 생각한다.” / scrapper@osen.co.kr
        </div>
        <div id="tab3" class="tab_cont">
            세번째 Tab 내용 입니다.
        </div>
    </div>
    <!--================ 콘텐츠영역 끝 ===============-->
    <!--================== 하단영역 =================-->
    ${nav_foot}
    <!--================ 하단영역 끝 ===============-->
</article>

<script>
    function ex1() {
        // 첫번째 Tab을 클릭했을 때 수행하는 곳

        // 현재 문서에서 아이디가 t1인 요소를 검색한다.
        var t1 = document.getElementById("t1");
        var t2 = document.getElementById("t2");
        var t3 = document.getElementById("t3");

        t1.className = "selected"; // <li class="selected".......
        t2.className = ""; // 기존에 있는 클래스 삭제
        t3.className = "";
        // ------------ 탭 처리 ----------------------
        // 해당 탭에 표현한 내용처리를 지금부터 하자!
        // 먼저 tab1, tab2, tab3 이라는 아이디를 가진 요소들을 모두 검색함
        var tab1 = document.getElementById("tab1");
        var tab2 = document.getElementById("tab2");
        var tab3 = document.getElementById("tab3");
        tab1.className = "tab_cont show";
        tab2.className = "tab_cont";
        tab3.className = "tab_cont";
    }
    function ex2() {
        // 첫번째 Tab을 클릭했을 때 수행하는 곳

        // 현재 문서에서 아이디가 t1인 요소를 검색한다.
        var t1 = document.getElementById("t1");
        var t2 = document.getElementById("t2");
        var t3 = document.getElementById("t3");

        t2.className = "selected"; // <li class="selected".......
        t1.className = ""; // 기존에 있는 클래스 삭제
        t3.className = "";
        // ------------ 탭 처리 ----------------------
        // 해당 탭에 표현한 내용처리를 지금부터 하자!
        // 먼저 tab1, tab2, tab3 이라는 아이디를 가진 요소들을 모두 검색함
        var tab1 = document.getElementById("tab1");
        var tab2 = document.getElementById("tab2");
        var tab3 = document.getElementById("tab3");
        tab2.className = "tab_cont show";
        tab1.className = "tab_cont";
        tab3.className = "tab_cont";
    }
    function ex3() {
        // 첫번째 Tab을 클릭했을 때 수행하는 곳

        // 현재 문서에서 아이디가 t1인 요소를 검색한다.
        var t1 = document.getElementById("t1");
        var t2 = document.getElementById("t2");
        var t3 = document.getElementById("t3");

        t3.className = "selected"; // <li class="selected".......
        t2.className = ""; // 기존에 있는 클래스 삭제
        t1.className = "";
        // ------------ 탭 처리 ----------------------
        // 해당 탭에 표현한 내용처리를 지금부터 하자!
        // 먼저 tab1, tab2, tab3 이라는 아이디를 가진 요소들을 모두 검색함
        var tab1 = document.getElementById("tab1");
        var tab2 = document.getElementById("tab2");
        var tab3 = document.getElementById("tab3");
        tab3.className = "tab_cont show";
        tab2.className = "tab_cont";
        tab1.className = "tab_cont";
    }
</script>
</body>
</html>
