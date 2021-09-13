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
               <h4 class="mb-4">
                   Contents of <%=siteName%>
               </h4>
               <table class="table">
                   <thead>
                     <tr>
                         <th>ID</th>
                         <th>Name</th>
                         <th>Surname</th>
                         <th>Salary</th>
                         <th>Club</th>
                         <th>Transfer Price</th>
                         <th>Year</th>
                         <th>Details</th>
                     </tr>
                   </thead>
                   <tbody>
                        <%
                            ArrayList<Footballers> footballers=(ArrayList<Footballers>) request.getAttribute("rating");

                            if(footballers!=null){
                                for (Footballers fb:footballers){
                        %>
                     <tr>
                         <td>
                             <%
                                 out.print(fb.getId());
                             %>
                         </td>
                         <td>
                             <%
                                 out.print(fb.getName());
                             %>
                         </td>
                         <td>
                             <%
                                 out.print(fb.getSurname());
                             %>
                         </td>
                         <td>
                             <%
                                 out.print(fb.getSalary());
                             %>
                         </td>
                         <td>
                             <%
                                 out.print(fb.getClub());
                             %>
                         </td>
                         <td>
                             <%
                                 out.print(fb.getTransferPrice());
                             %>
                         </td>
                         <td>
                             <%
                                 out.print(fb.getYear().getName() + " - " + fb.getYear().getShort_Name() );
                             %>
                         </td>
                         <td>
                            <a href="/details?id=<%=fb.getId()%>" class="btn btn-info btn-sm">Details</a>
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
