<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h2>세션에 저장된 값들:</h2>
  <h3>${sessionScope.pvo.s_name}</h3>
  <h3>${sessionScope.pvo.s_age}</h3>
  <h3>${sessionScope.pvo.s_email}</h3>
</body>
</html>
