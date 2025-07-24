<%@ page import="mybatis.vo.BbsVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="./css/summernote-lite.css"/>
    <style type="text/css">
        #bbs table {
            width:580px;
            margin-left:10px;
            border:1px solid black;
            border-collapse:collapse;
            font-size:14px;

        }

        #bbs table caption {
            font-size:20px;
            font-weight:bold;
            margin-bottom:10px;
        }

        #bbs table th {
            text-align:center;
            border:1px solid black;
            padding:4px 10px;
        }

        #bbs table td {
            text-align:left;
            border:1px solid black;
            padding:4px 10px;
        }

        .no {width:15%}
        .subject {width:30%}
        .writer {width:20%}
        .reg {width:20%}
        .hit {width:15%}
        .title{background:lightsteelblue}

        .odd {background:silver}
        .t_bold{ font-weight: bold; color: #007bff; }

    </style>
    <script type="text/javascript">
        function sendData(){
            /*for(var i=0 ; i<document.forms[0].elements.length ; i++){
              if(document.forms[0].elements[i].value == ""){
                alert(document.forms[0].elements[i].name+
                        "를 입력하세요");
                document.forms[0].elements[i].focus();
                return;//수행 중단
              }
            }*/

//		document.forms[0].action = "test.jsp";

            let title = $("#title").val();
            if(title.trim().length < 1){
                alert("제목을 입력하세요");
                $("#title").val("");
                $("#title").focus();
                return;
            }

            let writer = $("#writer").val();
            if(writer.trim().length < 1){
                alert("글쓴이를 입력하세요");
                $("#writer").val("");
                $("#writer").focus();
                return;
            }

            let content= $("#content").val();
            if(content.trim().length < 1){
                alert("내용을 입력하세요");
                $("#content").val("");
                $("#content").focus();
                return;
            }

            document.forms[0].submit();
        }
    </script>
</head>
<body>
<%
    Object obj = request.getAttribute("vo");
    if(obj != null){
        BbsVO vo = (BbsVO) obj;
%>
<div id="bbs">
    <form action="Controller?type=edit" method="post"
          encType="multipart/form-data">
        <input type="hidden" name="bname" value="BBS"/>
        <input type="hidden" name="b_idx" value="${param.b_idx}"/>
        <input type="hidden" name="cPage" value="${param.cPage}"/>
        <table summary="게시판 수정">
            <caption>게시판 수정</caption>
            <tbody>
            <tr>
                <th>제목:</th>
                <td><input type="text" name="title" id="title" size="45" value="<%=vo.getSubject()%>"/></td>
            </tr>
            <tr>
                <th>이름:</th>
                <td><input type="text" value="<%=vo.getWriter()%>"
                       name="writer" id="writer" size="12" disabled/></td>
            </tr>
            <tr>
                <th>내용:</th>
                <td><textarea name="content" cols="50"
                              id="content" rows="8"><%=vo.getContent()%></textarea></td>
            </tr>
            <tr>
                <th>첨부파일:</th>
                <td><input type="file" id="file" name="file"/>
                <%
                    if(vo.getFile_name() != null){
                %>
                    <p class="t_bold">(<%=vo.getFile_name()%>)</p>
                <%
                    }
                %>
                </td>
            </tr>
            <!--
                            <tr>
                                <th>비밀번호:</th>
                                <td><input type="password" name="pwd" size="12"/></td>
                            </tr>
            -->
            <tr>
                <td colspan="2">
                    <input type="button" value="수정"
                           onclick="sendData()"/>
                    <input type="button" value="취소" onclick="goBack()"/>
                    <input type="button" value="목록"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="./js/summernote-lite.js"></script>
<script src="./js/lang/summernote-ko-KR.js"></script>
<script>
    $(function (){

        $("#content").summernote({
            lang: "ko-KR",
            height: 300,
            callbacks: {
                onImageUpload: function(files, editor){
                    // 에디터에 이미지를 추가될 때 수행하는 곳!
                    // 이미지는 여러 개 추가할 수 있으므로 files는 배열이다.
                    for(let i=0; i<files.length; i++)
                        sendImg(files[i], editor);
                }
            }
        });

    });

    function sendImg(file, editor) {
        //서버로 비동기식 통신을 수행하기 위해 준비한다.
        // 이미지를 서버로 보내기위해 폼객체를 생성하자!
        let frm = new FormData();

        // 서버로 보낼 이미지파일을 폼객체에 파라미터로 지정
        frm.append("upload", file);

        //비동기식 통신
        $.ajax({
            url: "Controller?type=saveImg",
            data: frm,
            type: "post",
            contentType: false,
            processData: false,
            dataType: "json"
        }).done(function (res){
            // 요청 성공시 수행
            // 분명 서버의 saveImg.jsp에서 응답하는 json이
            // res로 들어온다. 그 json에 img_url이라는 이름으로
            // 이미지의 경로를 보내도록 되어 있다. 그것을 받아
            // editor에 img태그를 넣어주면 된다.
            $("#content").summernote("editor.insertImage", res.img_url);
        });
    }

    function goBack() {
        // Controller?type=view&b_idx=5&cPage=1
        location.href="Controller?type=view&b_idx=${param.b_idx}&cPage=${param.cPage}";
    }
</script>
<%
    }//if문의 끝
%>
</body>
</html>













