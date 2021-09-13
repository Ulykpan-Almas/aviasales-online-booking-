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
               <h4 class="mb-4">
                   Register  To System
               </h4>

               <%
                   String passError = request.getParameter("passworderror");
                   if(passError!=null){
               %>
               <div class="alert alert-danger alert-dismissible fade show" role="alert">
                   Password are not same
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
               <%
                   }
               %>



               <%
                   String emailError = request.getParameter("emailerror");
                   if(emailError!=null){
               %>
               <div class="alert alert-danger alert-dismissible fade show" role="alert">
                   User exists
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
               <%
                   }
               %>


               <%
                   String successfull = request.getParameter("successfull");
                   if(successfull!=null){
               %>
               <div class="alert alert-success alert-dismissible fade show" role="alert">
                   User Added
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
               <%
                   }
               %>






               <form action="/toregister" method="post">
                   <div class="form-group">
                       <label>Email : </label>
                       <input type="email" required class="form-control" name="email">
                   </div>
                   <div class="form-group">
                       <label>Password : </label>
                       <input type="password" required class="form-control" name="password">
                   </div>
                   <div class="form-group">
                       <label>Retype Password : </label>
                       <input type="password" required class="form-control" name="re_password">
                   </div>
                   <div class="form-group">
                       <label>Full Name : </label>
                       <input type="text" required class="form-control" name="full_name">
                   </div>
                   <div class="form-group">
                       <button class="btn btn-success">Register</button>
                   </div>

                   </div>
               </form>
           </div>
       </div>
   </div>
</body>
</html>
