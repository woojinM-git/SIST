<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./css/summernote-lite.css"/>
    <style>
        article .table {
            width: 600px;
        }
    </style>
</head>
<body>
    <div id="wrap">
        <header>
            <h1>썸머노트 웹에디터 연습</h1>
        </header>
        <article>
            <form>
                <table>
                    <tbody>
                        <tr>
                            <th class="w100">제목:</th>
                            <td>
                                <input type="text" name="title"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="w100">내용:</th>
                            <td>
                                <textarea id="content" rows="12" cols="50"></textarea>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="2">
                                <button type="button" id="send_btn">보내기</button>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </form>
        </article>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="./js/summernote-lite.js"></script> <!-- script는 닫는태그를 지정해야함 -->
    <script src="./js/lang/summernote-ko-KR.js"></script>
<script>
    $(function(){
       $('#content').summernote({
           lang: "ko-KR",
           height: 300,
           minHeight: 200,
           maxHeight: 400,
           width: 750,
           callbacks: {
               onImageUpload: function (files, editer) {
                   // 사용자가 이미지를 여러 개 넣을 수 있기 때문에 files라는
                   // 배열로 인식된다.
                   // 이것을 서버로 비동기식 통신을 수행하여 서버에게 업로드 시키도록 하자!
                   // console.log(files.length);
                   for(let i = 0; i < files.length; i++) {
                        // 이미지를 첨부파일로 서버로 전달하는 함수호출
                       sendImg(files[i], editer);
                   } // for end
               }
           }
       });
       $("#content").summernote("lineHeight", 1);

    });

    function sendImg(file, editor) {
        // console.log(file.name+":sendImage");

        // 서버로 이미지 파일을 보내기 위해서 반드시 form 객체
        let frm = new FormData(); // <form enctype="multipart/form-data"></form>

        // 서버로 보내고자 하는 자원의 폼에 파라미터 값으로 등록
        frm.append("upload", file); // 폼 객체 안에 "upload" 라는 이름으로
                                    // 파일이 등록된 상태다.

        // 비동기식 통신
        $.ajax({
            url: "Controller?type=saveImg",
            data: frm,
            type: "post",
            dataType: "json", // 서버에서 전달하는 자료의 자원의 자료형
            contentType: false,
            processData: false, // 이것을 지정해야 일반적인 데이터 전송이 아님을 증명한다.(파일첨부시 반드시 해야함)
        }).done(function(json){
            // 서버에 이미지를 성고적으로 업로드 시켰을 때 수행함
            $("#content").summernote("editor.insertImage", json.img_url);
            // insertImage 가 <img src . . .> 라는 태그를 에디터에 넣는 명령이다.
        });
    }
</script>
</body>
</html>
