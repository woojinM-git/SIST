        <%@ page import="mybatis.vo.MemVO" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    public int useSize(File f) {
        // 인자로 전달된 File객체가 폴더여야 한다.
        // 이 폴더의 하위요소들의 File 용량을 모두 더해야 한다. 우선
        // 하위요소들을 모두 얻어낸다.
        File[] list = f.listFiles();
        int size = 0;

        // 파일일 경우엔 용량을 size에 누적시키고, 폴더(디렉토리)일 경우엔
        // 현재 함수를 다시 호출(재귀호출)
        for (File sf : list) {
            if (sf.isFile())
                size += sf.length(); // 용량 누적
            else
                size += useSize(sf); // 재귀호출
        }
        return size;
    }
%>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">
    <style>
        table{
            width: 600px;
            border-collapse: collapse;
        }
        table th, table td {
            border: 1px solid #27A;
            padding: 5px;
        }
        table th {
            background-color: #bcbcbc;
        }
        .title{
            background-color: #bcbcbc;
            width: 25%;
        }
        .btn{
            display: inline-block;
            width: 70px;
            height: 20px;
            text-align: center;
            margin-right: 20px;
            padding:0px;
        }
        .btn a{
            display: block;
            width: 100%;
            padding: 4px;
            height: 20px;
            line-height: 20px;
            background: #27a;
            color: #fff;
            border-radius: 3px;
            text-decoration: none;
            font-size: 12px;
            font-weight: bold;
        }
        .btn a:hover{
            background: #fff;
            color: #27a;
            border: 1px solid #27a;
        }

        #s_id, #s_pw{
            width: 80px;
            border: 1px solid #27a;
            border-radius: 3px;
            padding: 4px;
        }
        div#log_fail, div#log_suc{
            width: 170px;
            border: 1px solid #27a;
            border-radius: 3px;
        }
        .hide{ display: none; }
        .show{ display: block; }

        div#box{
            display: inline-block;
            width: 65px;
            height: 20px;
            padding: 0;
            margin: 0;
            margin-left: 3px;
        }
        .success{color: #00f; font-weight: bold; font-size: 11px;}
        .fail{color: #f00; font-weight: bold; font-size: 11px;}
        div#my_alert{ display: none; }
        .w50{ width: 50px; }
        .w80{ width: 80px; }
    </style>
</head>
<body>
<%
    int totalsize = 1024 * 1024 * 10; // 10MByte
    int useSize = 0;
    // 로그인을 수행한 상태들만 허용하는 페이지이므로 로그인 검증하기
    Object obj = session.getAttribute("mvo");
    String dir;
    if (obj != null) { // 로그인을 수행한 경우
        MemVO mvo = (MemVO) obj;

        // 현재페이지로 올때 파라미터 하나 받는다. 그것은 바로 cPath다.
        // 만약 없으면 null을 받는다는 것을 기억하자!
        dir = request.getParameter("cPath");

        String fname = request.getParameter("f_name"); // 폴더명

        // 만약 dir이 null이면 접속한 사용자의 id를 넣어준다.
        if (dir == null) {
            dir = mvo.getM_id();
        } else {
            // 이미 myDisk에 들어왔다가 다른 작업(폴더)를 클릭하여 요청한 경우
            if (fname != null && fname.trim().length() > 0) {
                dir = dir + "/" + fname; // 아이디가 mmm 이라면 mmm/folder1
            }
        }
%>
    <h1>My Disk Service</h1>
    <hr/>
    <%=mvo.getM_name()%>(<span class="m_id"><%=mvo.getM_id()%></span>)님의 디스크&nbsp;[<a href="javascript:home()">Home</a>]
    <hr/>

    <table>
        <caption>디스크 사용량 테이블</caption>
        <tbody>
            <tr>
                <th class="title">전체용량</th>
                <td></td>
            </tr>
            <tr>
                <th class="title">사용량</th>
                <td></td>
            </tr>
            <tr>
                <th class="title">남은용량</th>
                <td></td>
            </tr>
        </tbody>
    </table>
    <hr/>
        <div id="btn_area">
            <p class="btn">
                <a href="javascript:selectFile()">파일올리기</a>
            </p>
            <p class="btn">
                <a href="javascript:makeFolder()">폴더생성</a>
            </p>
            <p class="btn">
                <a href="javascript:exe()">파일생성</a>
            </p>
        </div>
    <hr/>
    <lable for="dir">현재위치:</lable>
    <span id="dir"><%=dir%></span>

    <table>
        <caption>위치폴더 안에 내용을 표현하는 테이블</caption>
        <thead>
            <tr>
                <th class="w50">구분</th>
                <th>폴더 및 파일명</th>
                <th class="w80">삭제여부</th>
            </tr>
        </thead>
        <tbody>
        <%
            // [상위로] 기능 구현
            // 로그인한 사람의 id와 현재 위치(dir)가 다르면 [상위로] 버튼이 생겨야 함
            // 그리고 현재 위치와 로그인 id가 같으면 버튼은 보이지않아야한다.

            // 현재위치값(dir)을 가지고 File 객체를 만들기 위해 절대경로가 필요하다.
            System.out.println("test1"+dir);
            String realPath = application.getRealPath("/members/" + dir);
            System.out.println("test2"+dir);

            // 절대경로를 만든 이유는 File객체를 생성하여 하위에 있는 파일 또는 폴더들을
            // 얻기 위함이다.
            File s_file = new File(realPath); // C:...../members/(dir)
            // 위에서 만든 File객체안에 있는 하위요소(파일 및 폴더들) 얻기
            File[] sub_list = s_file.listFiles();
            for (File f : sub_list) {
        %>
                <tr>
                    <td>
                        <% if (f.isFile()) out.print("파일"); %>
                    </td>
                    <td>
                        <% if (f.isDirectory()) { // 디렉토리일 경우는 들어갈 수 있어야 한다.%>
                            <a href="javascript:gogo('<%=f.getName()%>')">
                                <%=f.getName()%>
                            </a>
                        <% } else { %>
                        <%=f.getName()%>
                        <% } %>
                    </td>
                    <td></td>
                </tr>
        <%
            } // for end
            if (mvo.getM_id() != dir) {
                dir = dir.substring(0, dir.lastIndexOf("/"));
                System.out.println("test"+dir);
            %>
                <button type="button" onclick="upfolder()">상위로</button>
            <% }
        %>
        </tbody>
    </table>

<form name="ff" method="post">
    <input type="hidden" name="f_name"/>
    <input type="hidden" name="cPath" value="<%=dir%>"/>
</form>

<%
    } else
        response.sendRedirect("../index.jsp");
%>

<script>
    function home(){
        location.href = "myDisk.jsp";
    }

    function gogo(folder_name){
        // 폴더를 클릭했을 때 해당 폴더명을 인자로 전달하여 수행하는 함수
//        alert(folder_name);

        // 현재문서(document)에서 이름이 ff인 폼 객체를 찾아 그 안에 이름이
        // f_name인 요소의 값(value)으로 인자로 받은 folder_name을 지정한다.
        document.ff.f_name.value = folder_name;
        document.ff.action = "myDisk.jsp";
        document.ff.submit();
    }

    function upfolder(){
        location.href = "myDisk.jsp";
    }
</script>
</body>
</html>
