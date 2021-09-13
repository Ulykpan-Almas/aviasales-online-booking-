<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.javaee.db.Footballers" %>
<%@ page import="kz.javaee.db.Blogs" %><%--
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
                   ArrayList<Blogs> blogs = (ArrayList<Blogs>) request.getAttribute("blogs");
                   if (blogs!=null){
                       for (Blogs b:blogs){
               %>
               <div class="jumbotron">
                   <h1><%=b.getTitle()%></h1>
                   <p class="lead"><%=b.getShortContent()%></p>
                   <hr class="my-4">
                   <p>Posted by <%=b.getUser().getFullName()%> at <%=b.getPostDate()%></p>
                   <p>
                       <span style="font-size: 14px;"><span id="like_amount"><%=b.getLikes()%></span> likes</span>
                   </p>
                   <a class="btn btn-primary btn-sm" href="/readblog?id=<%=b.getId()%>" role="button">Read more</a>
               </div>
               <%
                   }
                   }
               %>
           </div>
       </div>
   </div>
</body>
</html>
