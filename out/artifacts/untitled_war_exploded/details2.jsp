<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.javaee.db.Footballers" %>
<%@ page import="kz.javaee.db.Airlines" %><%--
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
                   Airlines airlines= (Airlines) request.getAttribute("airline");
                   if(airlines!=null){
               %>
               <%
                   if(currentUser!=null){
               %>

               <div class="jumbotron">
                   <h1 class="display-4">
                      From :  <%=airlines.getFrom_city()%>
                   </h1>
                   <p class="lead">
                      To :  <%=airlines.getTo_city()%>
                   </p>
                   <hr class="my-4">
                   <p>
                     This month :   <%=airlines.getWhen_flight()%>
                   </p>
                   <a class="btn btn-primary btn-sm" href="/edit2?id=<%=airlines.getId()%>" role="button">EDIT</a>
               </div>-

               <%
               }else {
               %>

               <div class="jumbotron">
                   <h1 class="display-4">
                       From :  <%=airlines.getFrom_city()%>
                   </h1>
                   <p class="lead">
                       To : <%=airlines.getTo_city()%>
                   </p>
                   <hr class="my-4">
                   <p>
                       This month :  <%=airlines.getWhen_flight()%>
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
