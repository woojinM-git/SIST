<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="/resources/css/summernote-lite.css"/>
  <meta charset="UTF-8">
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

      document.forms[0].submit(); // write 수행함
    }
  </script>
</head>
<body>
<div id="bbs">
  <form action="upload" method="post" encType="multipart/form-data">
    <input type="hidden" name="bname" value="BBS"/>
    <table summary="게시판 글쓰기">
      <caption>게시판 글쓰기</caption>
      <tbody>
      <tr>
        <th>제목:</th>
        <td><input type="text" name="subject" id="subject" size="45"/></td>
      </tr>
      <tr>
        <th>이름:</th>
        <td><input type="text" name="writer" id="writer" size="12"/></td>
      </tr>
      <tr>
        <th>내용:</th>
        <td><textarea name="content" cols="50"
               id="content" rows="8"></textarea></td>
      </tr>
      <tr>
        <th>첨부파일:</th>
        <td><input type="file" id="sss" name="sss"/></td>
      </tr>
      <!--
                      <tr>
                          <th>비밀번호:</th>
                          <td><input type="password" name="pwd" size="12"/></td>
                      </tr>
      -->
      <tr>
        <td colspan="2">
          <input type="button" value="올리기" id="upload"/>
          <input type="button" value="다시"/>
          <input type="button" value="목록" id="list"/>
        </td>
      </tr>
      </tbody>
    </table>
  </form>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="/resources/js/summernote-lite.js"></script> <!-- script는 닫는태그를 지정해야함 -->
<script src="/resources/js/lang/summernote-ko-KR.js"></script>
<script>
  $(function () {

    $('#upload').click(function () {
      // 유효성 검사 생략
      document.forms[0].submit();
    });

    $('#list').click(function () {
      location.href = "list?cPage=${param.cPage}";
    })

    $('#content').summernote({
      lang: "ko-KR",
      callbacks: {
        onImageUpload: function (files, editor) {
          for(let i = 0; i < files.length; i++)
            saveImage(files[i], editor);
        }
      }
    });

  });

  function saveImage(file, editor) {
    // 서버로 비동기식 통신을 통해 파일을 보내기 위해
    // 폼객체 준비
    let frm = new FormData();

    // 서버로 보내기 전에 폼객체에 파일을 파라미터로 지정하자!
    frm.append("upload", file);

    $.ajax({
      url: "saveImage",
      data: frm,
      type: "POST",
      contentType: false,
      processData: false, // 첨부파일을 보내는 것이고, 일반적인 데이터 전송이 아님을알린다.
      dataType: "json"
    }).done(function (res) {
      console.log(res);
      $("#content").summernote("editor.insertImage", res.url + "/" + res.fname);
    }).fail(function (err) {
      console.log("에러");
    })
  }

</script>
</body>
</html>













