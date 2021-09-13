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
                       String text = (String)request.getAttribute("text");
                       out.print(text);
                   %>

               </h3>

               <form action="/setsession" method="post">
                   <div class="form-group">
                        <label>Session Value</label>
                        <input type="text" name="session_value" class="form-control">
               </form>
               <button class="btn btn-success mt-2"> SET Session </button>
               <form action="/deletesession" method="post">
                   <button class="btn btn-danger mt-2">Delete Session</button>
               </form>
           </div>
       </div>
   </div>

       <div class="row mt-5">
           <div class="col-sm-6 offset-3">

               <h3>

                   <%
                        ArrayList <String> basket = (ArrayList <String>)request.getSession().getAttribute("basket");

                        if(basket!=null){
                            for (String bsk: basket){

                   %>
                   <h5 class="text-center">
                       <%=bsk%>
                   </h5>
                   <%
                       }
                       }
                   %>

               </h3>
           </div>
       </div>
   </div>
       <div class="row mt-5">
           <div class="col-sm-6 offset-3">
               <form action="/addtobasket" method="post">
                   <div class="form-group"
                   <label>Basket Value</label>
                   <input type="text" name="basket_value" class="form-control">
               </form>
               <button class="btn btn-success mt-2"> Add to Basket </button>
               <form action="/clearbasket" method="post">
                   <button class="btn btn-danger mt-2">Clear Basket</button>
               </form>
           </div>
       </div>
   </div>
</body>
</html>
