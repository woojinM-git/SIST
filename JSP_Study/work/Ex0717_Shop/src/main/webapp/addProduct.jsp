<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="sb" class="shop.bean.ShopBean" scope="session"/>
<jsp:useBean id="cart" class="shop.bean.Cart" scope="session"/>
<%
    String pnum = request.getParameter("p_num");
    cart.addProduct(sb, pnum);

    response.sendRedirect("product_content.jsp?p_num="+pnum);
%>