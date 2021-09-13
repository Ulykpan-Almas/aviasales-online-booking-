<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.javaee.db.*" %><%--
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
                   Airlines airlines= (Airlines) request.getAttribute("air");
                   if(airlines!=null){
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

               <form action="/edit2" method="post">
                   <input type="hidden" name="id" value="<%=airlines.getId()%>">
                   <div class="form-group">
                       <label>From : </label>
                       <input type="text" name="from" class="form-control" value="<%=airlines.getFrom_city()%>">
                   </div>
                   <div class="form-group">
                       <label>To : </label>
                       <input type="text" name="to" class="form-control" value="<%=airlines.getTo_city()%>">
                   </div>
                   <div class="form-group">
                       <label>When : </label>
                       <input type="number" name="when" class="form-control" value="<%=airlines.getWhen_flight()%>">
                   </div>
                   <div class="form-group">
                       <label>Passenger : </label>
                       <input type="number" name="passenger" class="form-control" value="<%=airlines.getPeople_number()%>">
                   </div>
                   <div class="form-group">
                       <button class="btn btn-success">Save</button>
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

                           <form action="/delete2" method="post">
                               <input type="hidden" name="id" value="<%=airlines.getId()%>">
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
