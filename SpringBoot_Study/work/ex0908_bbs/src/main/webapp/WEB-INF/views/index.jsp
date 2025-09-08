<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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
   
   #bbs table th,#bbs table td {
       text-align:center;
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
   
   /* paging */
   
   table tfoot ol.paging {
       list-style:none;
   }
   
   table tfoot ol.paging li {
       float:left;
       margin-right:8px;
   }
   
   table tfoot ol.paging li a {
       display:block;
       padding:3px 7px;
       border:1px solid #00B3DC;
       color:#2f313e;
       font-weight:bold;
   }
   
   table tfoot ol.paging li a:hover {
       background:#00B3DC;
       color:white;
       font-weight:bold;
   }
   
   .disable {
       padding:3px 7px;
       border:1px solid silver;
       color:silver;
   }
   
   .now {
      padding:3px 7px;
       border:1px solid #ff4aa5;
       background:#ff4aa5;
       color:white;
       font-weight:bold;
   }
      
</style>
</head>
<body>
<div id="wrap">
    <button type="button" 
        onclick="javascript:location.href='list?bname=BBS'">게시판</button>
    <button type="button" 
        onclick="javascript:location.href='list?bname=NOTICE'">공지사항</button>
</div>


</body>
</html>
