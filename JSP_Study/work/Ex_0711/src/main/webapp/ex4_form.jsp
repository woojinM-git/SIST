<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<article>
    <header>
        <h2>폼 연습2</h2>
    </header>
    <div>
        <form action="ex4.jsp" method="post" name="frm">
            <label for="s_int">숫자입력:</label>
            <input type="text" id="s_int" name="s_int"/><br>
            <button type="button" onclick="exe()">보내기</button>
        </form>
    </div>
</article>

<script>
    function exe(){
        // 유효성 검사
        let num = document.getElementById("s_int");
        // 값을 입력했는지 확인
        if(num.value.trim().length == 0){
            alert("숫자를 입력하세요");
            num.value = "";
            num.value.focus();
            return;
        }
        // 숫자인지 아닌지? 판단해야함
        if(!isNaN(num)){
            alert("숫자를 입력하세요")
            num.value = "";
            num.value.focus();
            return;
        }
        document.frm.submit();
    }
</script>
</body>
</html>
