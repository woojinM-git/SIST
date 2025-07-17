<%@ page import="mybatis.vo.ProductVO" %>
<%@ page import="mybatis.dao.ShopDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<div id="wrap">
    <header>
        <jsp:include page="./menu.jsp"/> <!-- 다른 jsp를 이 부분에 포함시킨다  -->
    </header>

    <article>
        <%--
        ShopBean이라는 빈을 재 사용하자!
        앞서 product_list.jsp에서 useBean으로 사용된 shopBean을 다시 세션으로부터
        가져와서 재 사용하면 ShopBean에 있는 p_list를 채워진 상태에서
        재 사용이 가능하다. 이렇게 하기 위해서는 반드시
        product_list.jsp에서 ShopBean을 useBean으로 정의하는 내용을 아래와 같이
        복사해서 붙여넣으면 된다.
        --%>
        <jsp:useBean id="sb" class="shop.bean.ShopBean" scope="session"/>

        <table class="table">
            <thad>
                <tr>
                    <td colspan="6">:: 장바구니 내용</td>
                </tr>
                <tr bgcolor="#dedede">
                    <th>제품번호</th>
                    <th width="25%">제품명</th>
                    <th>단가</th>
                    <th>수량</th>
                    <th>금액</th>
                    <th>삭제</th>
                </tr>
            </thad>
            <tbody>
            <jsp:useBean id="cart" class="shop.bean.Cart" scope="session"/>
            <%
                // 장바구니에 저장된 모든 제품들을 배열로 가져온다.
                ProductVO[] ar = cart.getList();
                int total = 0; // 총액

                if(ar != null){
                    for(ProductVO vo : ar) {
            %>
                <tr align="center">
                    <td><%=vo.getP_num()%></td>
                    <td><%=vo.getP_name()%></td>
                    <td>
                        정가: <%=vo.getP_price()%><br>
                        <font color="red">
                            세일가격: <%=vo.getP_saleprice()%>원
                        </font>
                    </td>
                    <td>
                        <!-- 수량 조정 폼 -->
                        <form action="changeCount.jsp" method="post">
                            <input type="hidden" name="p_num" value="<%=vo.getP_num()%>"/>
                            <input type="number" name="count" value="<%=vo.getQuant()%>" min="1" max="99"/>
                            <button type="button" onclick="ch(this.form)">
                                변경
                            </button>
                        </form>
                        <!------------------>
                    </td>
                    <td><%=vo.getTotalPrice()%></td>
                    <td>
                        <input type="button" value="삭제"
                               onclick="javascript:location.href='delProduct.jsp?p_num=<%=vo.getP_num()%>'">
                    </td>
                </tr>
            <%
                        // 총액 누적
                    total += vo.getTotalPrice();
                    } // for end
                } else { // 장바구니가 비어있을 때
            %>

                <tr align="center">
                    <td colspan="6">
                        <b>장바구니가 비었습니다.</b>
                    </td>
                </tr>
            <%
                } // if end
            %>
                <tr>
                    <td colspan="5" align="right">총 결재액 : </td>
                    <td><%=total%></td>
                </tr>

            </tbody>
        </table>
    </article>
</div>

<script>
 function ch(f) {
     f.submit();
 }
</script>
</body>
</html>
