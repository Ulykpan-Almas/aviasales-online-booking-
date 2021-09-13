<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.javaee.db.Footballers" %>
<%@ page import="kz.javaee.db.Countries" %>
<%@ page import="kz.javaee.db.DBManager" %><%--
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
                   Contents of <%=siteName%>
               </h4>
               <%
                   String success = request.getParameter("success");
                  if(success!=null){
               %>
               <div class="alert alert-success alert-dismissible fade show" role="alert">
                   Footballer added
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
               <%
                   }
               %>
               <form action="/addFootballer" method="post">
                   <div class="form-group">
                       <label>Name : </label>
                       <input type="text" name="name" class="form-control">
                   </div>
                   <div class="form-group">
                       <label>Surname : </label>
                       <input type="text" name="Surname" class="form-control">
                   </div>
                   <div class="form-group">
                       <label>Salary : </label>
                       <input type="number" name="Salary" class="form-control">
                   </div>
                   <div class="form-group">
                       <label>Club : </label>
                       <input type="text" name="Club" class="form-control">
                   </div>
                   <div class="form-group">
                       <label>Transfer Price : </label>
                       <input type="number" name="Transfer" class="form-control">
                   </div>
                   <div class="form-group">
                       <label>Year : </label>
                      <select class="form-control" name="year">
                       <%
                           ArrayList<Countries> countries = (ArrayList<Countries>) request.getAttribute("countries");
                           if(countries!=null){
                               for(Countries c: countries){

                       %>
                          <option value="<%=c.getId()%>">
                             <%=c.getName() + " / " + c.getShort_Name()%>
                          </option>
                       <%
                           }
                           }
                       %>
                      </select>
                   </div>
                   <div class="form-group">
                      <button class="btn btn-success">ADD Footballer</button>
                   </div>
               </form>
           </div>
       </div>
   </div>
</body>
</html>
