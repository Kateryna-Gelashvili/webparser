<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.k.Product" %>
<%@ page import="org.k.DataBaseService" %>
<%--
  Created by IntelliJ IDEA.
  User: Kateryna
  Date: 28.07.2015
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Price list</title>
      <link rel = "stylesheet" type="text/css" href="webCSS.css"/>
  </head>
  <body>
  <h2>Price list of laptops from rozetka</h2>
  <table class="list">
      <tr class="list">
          <th class="name">Name</th>
          <th>Price</th>
          <th>Currency</th>
          <th>Website</th>
      </tr>

      <%
        ArrayList<Product> orderedList =(ArrayList<Product>) request.getAttribute("priceList");
          if(orderedList!=null && orderedList.size()>0 ){
              for (Product product: orderedList){
      %>

      <tr>
          <td><%=product.getName() %></td>
          <td class="price"><%=product.getPrice() %></td>
          <td><%=product.getCurrency() %></td>
          <td <a href= "http://www.<%=product.getWebsite() %>"><%=product.getWebsite() %></a> </td>
      </tr>
      <% } %>
      <% } %>
  </table>

  </body>
</html>
