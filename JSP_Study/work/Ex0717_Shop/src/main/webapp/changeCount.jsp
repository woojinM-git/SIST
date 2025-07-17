<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="cart" class="shop.bean.Cart" scope="session"/>
<%
  String pnum = request.getParameter("p_num");
  String count = request.getParameter("count");

  if(pnum != null && count != null){
    int q = Integer.parseInt(count);
    if(q < 1)
      cart.delProduct(pnum);
    else
      cart.changeCount(pnum, q);
  }

  response.sendRedirect("cartList.jsp");
%>