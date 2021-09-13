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
               <h4 class="mb-4">
                   Search for cheap Flight
               </h4>
               <table class="table">
                   <thead>
                     <tr>
                         <th>ID</th>
                         <th>From</th>
                         <th>To</th>
                         <th>When</th>
                         <th>Passenger</th>
                         <th></th>
                     </tr>
                   </thead>
                   <tbody>
                        <%
                            ArrayList<Airlines> airlines=(ArrayList<Airlines>) request.getAttribute("air");

                            if(airlines!=null){
                                for (Airlines ar:airlines){
                        %>
                     <tr>
                         <td>
                             <%
                                 out.print(ar.getId());
                             %>
                         </td>
                         <td>
                             <%
                                 out.print(ar.getFrom_city());
                             %>
                         </td>
                         <td>
                             <%
                                 out.print(ar.getTo_city());
                             %>
                         </td>
                         <td>
                             <%
                                 out.print(ar.getWhen_flight());
                             %>
                         </td>
                         <td>
                             <%
                                 out.print(ar.getPeople_number());
                             %>
                         </td>
                         <td>
                            <a href="/details2?id=<%=ar.getId()%>" class="btn btn-info btn-sm">Details</a>
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
