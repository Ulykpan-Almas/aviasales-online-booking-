<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.javaee.db.Footballers" %><%--
  Created by IntelliJ IDEA.
  User: Almas
  Date: 19.01.2021
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
     <%@include file="wendor/head.jsp"%>
</head>
<body>
  <%@include file="wendor/navbar.jsp"%>
   <div class="container">
       <div class="row mt-5">
           <div class="col-sm-6 offset-3">

               <h3>
                   <%
                       String cookieValue=(String)request.getAttribute("cookieValue");
                       out.print("Your Cookie : "+cookieValue);
                   %>
               </h3>

               <form action="/setcookie" method="post">
                   <div class="form-group"
                        <label>Cookie</label>
                        <input type="text" name="cookie_value" class="form-control">
               </form>
               <button class="btn btn-success mt-2"> SET Cookie </button>
               <form action="/deletecookie" method="post">
                   <button class="btn btn-danger mt-2">Delete Cookie</button>
               </form>
           </div>
       </div>
   </div>
</body>
</html>
