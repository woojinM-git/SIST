<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>JSON TEST</title>
  <meta charset="utf-8">
  <style>
    #box {
      width: 300px;
      height: 250px;
      border: 1px solid black;
    }
  </style>
</head>
<body>
  <h1>JSON 연습입니다</h1>
  <button type="button" id="btn">확인</button>
  <br/>
  <div id="box"></div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
  $(function () {
    // 무조건 수행하는 곳!
    // 여기서 버튼에 대한 이벤트 등록
    $("#btn").click(function () {
     // 버튼을 클릭할 때마다 수행하는 곳!
     // console.log("^^")
      $.ajax({
        url: "ex3",
        data: "v1=100&v2=200",
        // type: "get",
        type: "post",
        dataType: "json"
      }).done(function (res) {
        // console.log(res);
        /*
        let str = "";
        str += "<tr><td>Name:</td><td>";
        str += res.name;
        str += "</td></tr><tr><td>Email:</td><td>";
        str += res.email;
        str += "</td></tr>";
         */
        $("#box").html("<h2>Name:" + res.name + "</h2><br/><h2>Email:" + res.email + "</h2>");
      })
    });
  });
</script>
</body>
</html>
