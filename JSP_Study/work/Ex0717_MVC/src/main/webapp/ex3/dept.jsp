<%@ page import="mybatis.vo.EmpVO" %>
<%@ page import="mybatis.vo.DeptVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #table{
            width: 600px;
            border-collapse: collapse;
        }
        #table th, #table td{
            border: 1px solid black;
            padding: 5px;
        }
        #table caption{
            text-indent: -999999px; /* 화면상에서 안보이게 함 */
        }
    </style>
</head>
<body>
<div id="wrap">
    <header>
        <h2>부서목록</h2>
    </header>
    <article>
        <table id="table">
            <caption>부서목록 테이블</caption>
            <thead>
            <tr>
                <th>부서코드</th>
                <th>부서이름</th>
                <th>loc_code</th>
            </tr>
            </thead>
            <tbody>
            <%
                // request에 emp라는 이름으로 저장된 객체를 가져온다.
                Object obj = request.getAttribute("dept");

                DeptVO[] ar = null;
                if(obj != null){
                    ar = (DeptVO[]) obj;
                    for(DeptVO vo : ar){
            %>
            <tr>
                <td><%=vo.getDeptno()%></td>
                <td><%=vo.getDname()%></td>
                <td><%=vo.getLoc_code()%></td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </article>
</div>
</body>
</html>
