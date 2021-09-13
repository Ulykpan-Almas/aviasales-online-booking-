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
           <div class="col-sm-12">
               <%
                   Footballers footballers= (Footballers)request.getAttribute("football");
                   if(footballers!=null){
               %>

               <%
                   String success = request.getParameter("success");
                   if(success!=null){
               %>
               <div class="alert alert-success alert-dismissible fade show" role="alert">
                   Footballer saved
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
                   Wrong
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
               <%
                   }
               %>

               <form action="/edit" method="post">
                   <input type="hidden" name="id" value="<%=footballers.getId()%>">
                   <div class="form-group">
                       <label>Name : </label>
                       <input type="text" name="name" class="form-control" value="<%=footballers.getName()%>">
                   </div>
                   <div class="form-group">
                       <label>Surname : </label>
                       <input type="text" name="Surname" class="form-control" value="<%=footballers.getSurname()%>">
                   </div>
                   <div class="form-group">
                       <label>Salary : </label>
                       <input type="number" name="Salary" class="form-control" value="<%=footballers.getSalary()%>">
                   </div>
                   <div class="form-group">
                       <label>Club : </label>
                       <input type="text" name="Club" class="form-control" value="<%=footballers.getClub()%>">
                   </div>
                   <div class="form-group">
                       <label>Transfer Price : </label>
                       <input type="number" name="Transfer" class="form-control" value="<%=footballers.getTransferPrice()%>">
                   </div>
                   <div class="form-group">
                       <label>Year : </label>
                       <select class="form-control" name="year">
                           <%
                               ArrayList<Countries> countries = (ArrayList<Countries>) request.getAttribute("countries");
                               if(countries!=null){
                                   for(Countries c: countries){

                           %>
                           <option value="<%=c.getId()%>" <% if(c.getId()==footballers.getYear().getId()){ out.print("selected");}%>>
                               <%=c.getName() + " / " + c.getShort_Name()%>
                           </option>
                           <%
                                   }
                               }
                           %>
                       </select>
                   </div>
                   <div class="form-group">
                       <button class="btn btn-success">Save Footballer</button>
                       <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deletefootballer">
                           Delete
                       </button>
                   </div>
               </form>

               <!-- Button trigger modal -->


               <!-- Modal -->
               <div class="modal fade" id="deletefootballer" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                   <div class="modal-dialog">
                       <div class="modal-content">

                           <form action="/delete" method="post">
                               <input type="hidden" name="id" value="<%=footballers.getId()%>">
                           <div class="modal-header">
                               <h5 class="modal-title" id="staticBackdropLabel">Confirm</h5>
                               <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                   <span aria-hidden="true">&times;</span>
                               </button>
                           </div>
                           <div class="modal-body">
                               Sure?
                           </div>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                               <button class="btn btn-danger">Yes</button>
                           </div>
                           </form>
                       </div>
                   </div>
               </div>

               <%
                   }
               %>
           </div>
       </div>
   </div>
</body>
</html>
