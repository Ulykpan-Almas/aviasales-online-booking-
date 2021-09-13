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
    <script type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
    <script>tinymce.init({selector:'textarea'});</script>
</head>
<body>
  <%@include file="wendor/navbar.jsp"%>
   <div class="container">
       <div class="row mt-5">
           <div class="col-sm-12">
               <%
                   String success = request.getParameter("success");
                   if(success!=null){
               %>
               <div class="alert alert-success alert-dismissible fade show" role="alert">
                   Blog added
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
               <%
                   }
               %>
               <form action="/addblog" method="post">
                   <div class="form-group">
                       <label>Title: </label>
                       <input type="text" class="form-control" name="title">
                   </div>
                   <div class="form-group">
                       <label>Short Content: </label>
                       <textarea class="form-control" name="short_content" rows="5"></textarea>
                   </div>
                   <div class="form-group">
                       <label>Content: </label>
                       <textarea class="form-control" name="content" rows="10"></textarea>
                   </div>
                   <div class="form-group">
                       <button class="btn btn-success">ADD Blog</button>
                   </div>
               </form>
           </div>
       </div>
   </div>
</body>
</html>
