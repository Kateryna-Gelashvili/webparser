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
        <form action="Search-parameters" method="get">
            <table>
                <tr>
                    <td width="172"><b>Search by name</b></td>
                    <td><input type="text" name="SearchByName"></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td width="170"><b>Min price</b></td>
                    <td><input type="number" name="MinPrice"></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td width="170"><b>Max price</b></td>
                    <td><input type="number" name="MaxPrice"></td>
                </tr>
             </table>
                    <tr>
                        <td><input type="submit" value="Search"></td>
                    </tr>
        </form>
  <table class="list">
      <tr class="list">
          <th class="name">Name</th>
          <th>Price</th>
          <th>Currency</th>
          <th>Website</th>
      </tr>
  <%
      ArrayList<Product> priceList = DataBaseService.getListOfLaptops();
      if(priceList!=null && priceList.size()>0 ){
          for (Product product: priceList){
  %>

      <tr>
          <td><%=product.getName() %></td>
          <td class="price"><%=product.getPrice() %></td>
          <td><%=product.getCurrency() %></td>
          <td><a href= "http://www.<%=product.getWebsite() %>"><%=product.getWebsite() %></a></td>
      </tr>
         <% } %>
  <% } %>
  </table>

  </body>
</html>
