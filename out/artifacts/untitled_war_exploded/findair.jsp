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
    <script type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
    <script>tinymce.init({selector:'textarea'});</script>
</head>
<body style="background-color: #007bff;>
  <%@include file="wendor/navbar.jsp"%>


  <div class="container">
       <div class="row mt-5">
           <div class="col-sm-12">

               <form action="/search2" method="get">
                   <h1 style="color: white ; text-align: center ; font-weight: bold" >Поиск Дешевых Авиабилетов</h1>
                   <h6 style="color: white ; text-align: center ; font-weight: bold" >Лучший способ купить авиабилеты дёшево</h6>
                   <div class="form-row align-items-center">
                       <div class="col my-1 mt-4 ">
                           <label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
                           <input style="height: 38px;" placeholder="From" type="text" name="key" class="form-control form-control-sm" value="<%=(request.getParameter("key")!=null?request.getParameter("key"):"")%>">
                       </div>
                       <div class="col my-1 mt-4">
                           <label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
                           <input type="text" class="form-control" placeholder="To" name="to" value="<%=(request.getParameter("to")!=null?request.getParameter("to"):"")%>">
                       </div>
                       <form>
                           <div class="col my-1 mt-4">
                               <input type="number" class="form-control" placeholder="When" name="when" value="<%=(request.getParameter("when")!=null?request.getParameter("when"):"")%>">
                           </div>
                       </form>

                       <div class="col my-1 mt-4">
                           <label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
                           <select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                               <option selected value="1">1</option>
                               <option value="2">2</option>
                               <option value="3">3</option>
                               <option value="4">4</option>
                               <option value="5">5</option>
                           </select>
                       </div>


                       <div class="col my-1 mt-4 pt-2">
                           <button style="background-color: #ff6f32 ; color: white ; width: 220px" type="submit" class="btn mb-2">Find</button>
                       </div>
                   </div>

               </form>


               <h3 class="mt-3" style="color: white" >
                   Search results for key: "<%=(request.getParameter("key")!=null?request.getParameter("key"):"")%>" + "<%=(request.getParameter("to")!=null?request.getParameter("to"):"")%>"
               </h3>
               <table class="table table-striped mt-3" bgcolor="white">
                   <thead>
                   <tr>
                       <th>ID</th>
                       <th>From</th>
                       <th>To</th>
                       <th>When</th>
                       <th>Passenger</th>
                   </tr>
                   </thead>
                   <tbody>
                   <%
                       ArrayList<Airlines> airlines = (ArrayList<Airlines>)request.getAttribute("air");
                       if(airlines!=null){
                           for(Airlines it : airlines){
                   %>
                   <tr>
                       <td>
                           <%=it.getId()%>
                       </td>
                       <td>
                           <%=it.getFrom_city()%>
                       </td>
                       <td>
                           <%=it.getTo_city()%>
                       </td>
                       <td>
                           <%=it.getWhen_flight()%>
                       </td>
                       <td>
                           <%=it.getPeople_number()%>
                       </td>
                   </tr>
                   <%
                           }
                       }
                   %>
                   </tbody>
               </table>






           </div>
       </div>
   </div>
</body>
</html>
