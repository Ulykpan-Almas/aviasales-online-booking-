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
                   if(currentUser!=null){
               %>


               <%
                   String success = request.getParameter("success");
                   if(success!=null){
               %>
               <div class="alert alert-success alert-dismissible fade show" role="alert">
                   Profile Updated
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
               <%
                   }
               %>



               <form action="/updateprofile" method="post">
               <div class="form-group">
                   <label>Email : </label>
                   <input type="email" value="<%=currentUser.getEmail()%>" class="form-control" readonly>
               </div>
               <div class="form-group">
                   <label>Full Name : </label>
                   <input type="text" name="full_name" value="<%=currentUser.getFullName()%>" class="form-control">
               </div>
               <div class="form-group">
                   <button class="btn btn-success">Update Profile</button>
               </div>
               </form>
               <%
                   }
               %>
           </div>
       </div>


       <div class="row mt-5">
           <div class="col-sm-12">
               <%
                   if(currentUser!=null){
               %>


               <%
                   String passwordsuccess = request.getParameter("passwordsuccess");
                   if(passwordsuccess!=null){
               %>
               <div class="alert alert-success alert-dismissible fade show" role="alert">
                   Password Updated
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
               <%
                   }
               %>

               <%
                   String error = request.getParameter("error");
                   if(error!=null){
               %>
               <div class="alert alert-danger alert-dismissible fade show" role="alert">
                   Error Update
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
               <%
                   }
               %>


               <form action="/updatepassword" method="post">
                   <div class="form-group">
                       <label>OLD Password : </label>
                       <input type="password" name="old_password" class="form-control" >
                   </div>
                   <div class="form-group">
                       <label>New Password : </label>
                       <input type="password" name="new_password" class="form-control" >
                   </div>
                   <div class="form-group">
                       <label>Retype Password : </label>
                       <input type="password" name="re_password" class="form-control" >
                   </div>
                   <div class="form-group">
                       <button class="btn btn-success">Update Password</button>
                   </div>
               </form>
               <%
                   }
               %>
           </div>
       </div>
   </div>
</body>
</html>
