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
           <div class="col-sm-12">



               <%
                   Footballers footballers= (Footballers)request.getAttribute("football");
                   if(footballers!=null){
               %>
               <%
                   if(currentUser!=null){
               %>

               <div class="jumbotron">
                   <h1 class="display-4">
                       <%=footballers.getName()%>
                   </h1>
                   <p class="lead">
                       PRICE : <%=footballers.getTransferPrice()%>USD
                   </p>
                   <hr class="my-4">
                   <p>
                       <%=footballers.getClub()%>
                   </p>
                   <a class="btn btn-primary btn-sm" href="/edit?id=<%=footballers.getId()%>" role="button">EDIT</a>
               </div>-

               <%
               }else {
               %>

               <div class="jumbotron">
                   <h1 class="display-4">
                       <%=footballers.getName()%>
                   </h1>
                   <p class="lead">
                       PRICE : <%=footballers.getTransferPrice()%>USD
                   </p>
                   <hr class="my-4">
                   <p>
                       <%=footballers.getClub()%>
                   </p>
               </div>

               <%
                   }
               %>

               <%
                   }
               %>



           </div>
       </div>
   </div>
</body>
</html>
