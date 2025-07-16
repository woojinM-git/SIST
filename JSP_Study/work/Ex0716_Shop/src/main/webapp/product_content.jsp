<%@ page import="mybatis.vo.ProductVO" %>
<%@ page import="mybatis.dao.ShopDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="./css/style.css">
    <style>
        table.table th, table.table td {
            border: 1px solid navy;
            padding: 5px;
            font-size: 18px;
        }
    </style>
</head>
<body>
<div id="wrap">
    <header>
        <jsp:include page="./menu.jsp"/> <!-- 다른 jsp를 이 부분에 포함시킨다  -->
    </header>
    <!-- table -->
    <article>
        <%
            request.setCharacterEncoding("UTF-8");

            // prod_num 파라미터 받기
            String p_num = request.getParameter("prod_num");

            ProductVO pvo = ShopDAO.getProduct(p_num);
        %>
        <table class="table">
            <colgroup>
                <col width="40%">
                <col width="60%">
            </colgroup>
            <tbody>
            <tr>
                <td>제품Category</td>
                <td><%=pvo.getCategory()%></td>
            </tr>
            <tr>
                <td>제품번호</td>
                <td><%=pvo.getP_num()%></td>
            </tr>
            <tr>
                <td>제품이름</td>
                <td><%=pvo.getP_name()%></td>
            </tr>
            <tr>
                <td>제품 판매사</td>
                <td><%=pvo.getP_company()%></td>
            </tr>
            <tr>
                <td>제품가격: <%=pvo.getP_price()%>원</td>
                <td>(할인가 : <%=pvo.getP_saleprice()%>원 )</td>
            </tr>
            <tr>
                <td colspan="2">제품설명<br/>
                <%=pvo.getP_content()%>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><img src ="images/<%=pvo.getP_image_l()%>" width="300" height="295"></td>
            </tr>
            <tr>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="button" value="장바구니에 담기"
                           onclick="javascript:location.href='addProduct.jsp?p_num='"/>
                    <input type="button" value="장바구니 보기"
                           onclick="javascript:location.href='cartList.jsp'"/>
                </td>
            </tr>
            </tbody>
        </table>
    </article>
</div>

<script>

</script>
</body>
</html>
