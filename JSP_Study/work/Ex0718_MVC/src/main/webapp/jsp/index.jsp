<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">
    <style>
        .table {
            width: 600px;
            border-collapse: collapse;
        }
        .table th, .table td {
            border: 1px solid #000;
            padding: 5px;
        }
        .table caption {
            text-indent: -99999px;
            height: 0;
        }
        .txt_R {
            text-align: right;
        }
        .noBorder {
            border: none !important; /* 우선권을 주는 !important */
        }
        #dialog {
            display: none;
        }
        table.dialog {
            padding: 5px;
        }
    </style>
</head>
<body>
    <div id="wrap">
        <header>
            <h1>사원 목록</h1>
        </header>
        <!-- 사번 이름 직종 급여 입사일 부서코드 -->
        <article>
            <table class="table">
                <caption>사원 테이블</caption>
                <thead>
                <tr>
                    <td colspan="6" class="txt_R noBorder">
<%--                        <button type="button" id="total_btn" onclick="javascript:location.href='Controller?type=total'">전체보기</button>--%>
                        <button type="button" id="all_btn">전체(비동기식)</button>
                        <button type="button" id="total_btn" onclick="total()">전체보기</button>
                        <button type="button" id="search_btn">검색</button>
                        <button type="button" id="add_btn" onclick="javascript:location.href='Controller?type=add'">추가</button>
                        <button type="button" id="dept_btn" onclick="dept()">부서목록</button>
                    </td>
                </tr>
                <tr>
                    <th>사번</th>
                    <th>이름</th>
                    <th>직종</th>
                    <th>급여</th>
                    <th>입사일</th>
                    <th>부서코드</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </article>
    </div>

    <!-- Dialog -->
    <div id="search_dig">
        <form>
            <table>
                <tbody>
                <tr>
                    <td>
                        <select id="searchType" name="searchType">
                            <option value="0">사번</option>
                            <option value="1">이름</option>
                            <option value="2">직종</option>
                            <option value="3">부서코드</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" id="searchValue" name="searchValue"/>
                    </td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="2">
                        <button type="button" id="send" class="btn">검색</button>
                    </td>
                </tr>
                </tfoot>
            </table>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>
<script>
    $(function () {
        $("#all_btn").click(function () {
            $.ajax({
                url: "Controller",
                type: "post",
                data: {type: "all"}
            }).done(function (res) {
                console.log(res); <!-- res의 담긴 값을 보기 위한 console.log -->
                <!-- res에는 all.jsp에서 반복문이 구동되어 쌓인 결과가 저장되고 -->
                $("table.table>tbody").html(res); <!-- res에 담긴 <tr>태그들을 "table.table>tbody"의 자리에 넣어준다 -->
            });
        });

        let option = {
            modal: true,
            autoOpen: false,
            title: '검색',
            resizable: false
        };

        $("#search_dig").dialog(option); // 다이얼로그창 등록

        $("#search_btn").click(function (){
            $("#search_dig").dialog("open");
        });

        $("#send").click(function (){
            $.ajax({
                url: "Controller",
                type: "post",
                data: {type: "search",
                searchType: $("#searchType").val(),
                searchValue: $("#searchValue").val()}
            }).done(function (res) {
                $("#search_dig").dialog("close");
                console.log(res); <!-- res의 담긴 값을 보기 위한 console.log -->
                <!-- res에는 all.jsp에서 반복문이 구동되어 쌓인 결과가 저장되고 -->
                $("table.table>tbody").html(res); <!-- res에 담긴 <tr>태그들을 "table.table>tbody"의 자리에 넣어준다 -->
            });
        })
    });
    function total(){
        location.href = "Controller?type=total";
    }
    function dept(){
        location.href = "Controller?type=dept";
    }
</script>
</body>
</html>