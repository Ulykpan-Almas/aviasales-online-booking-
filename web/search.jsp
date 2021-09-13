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
      <div class="row mt-3">
          <div class="col-12">
              <form action="/search" method="get">
                  <div class="row">
                      <div class="col-3">
                          NAME :
                      </div>
                      <div class="col-3">
                          COUNTRY :
                      </div>
                      <div class="col-4">
                          PRICE RANGE :
                      </div>
                      <div class="col-2">

                      </div>
                  </div>
                  <div class="row mt-2">
                      <div class="col-3">
                          <input type="text" name="key" class="form-control form-control-sm" value="<%=(request.getParameter("key")!=null?request.getParameter("key"):"")%>">
                      </div>
                      <div class="col-3">
                          <select class="form-control form-control-sm" name="country_id">
                              <option value = "0">
                                  All Countries
                              </option>
                              <%
                                  Long countryId = 0L;
                                  try {
                                      countryId = Long.parseLong(request.getParameter("country_id"));
                                  }catch (Exception e){

                                  }
                                  ArrayList<Countries> countries = (ArrayList<Countries>)request.getAttribute("countries");
                                  if(countries!=null){
                                      for (Countries c : countries){
                              %>
                              <option value="<%=c.getId()%>" <%=(countryId==c.getId()?"selected = 'selected'":"")%>>
                                  <%=c.getName() + " / " + c.getShort_Name()%>
                              </option>
                              <%
                                      }
                                  }
                              %>
                          </select>
                      </div>
                      <div class="col-4">
                          <div class="row">
                              <div class="col-6">
                                  <input type="number" name="from_price" class="form-control form-control-sm" value="<%=(request.getParameter("from_price")!=null?request.getParameter("from_price"):"")%>">
                              </div>
                              <div class="col-6">
                                  <input type="number" name="to_price" class="form-control form-control-sm" value="<%=(request.getParameter("to_price")!=null?request.getParameter("to_price"):"")%>">
                              </div>
                          </div>
                      </div>
                      <div class="col-2">
                          <button class="btn btn-success btn-sm btn-block">SEARCH</button>
                      </div>
                  </div>
              </form>
              <h3 class="mt-3">
                  Search results for key: "<%=(request.getParameter("key")!=null?request.getParameter("key"):"")%>"
              </h3>
              <table class="table table-striped mt-3">
                  <thead>
                  <tr>
                      <th>ID</th>
                      <th>NAME</th>
                      <th>SURNAME</th>
                      <th>Salary</th>
                      <th>Club</th>
                      <th>TransferPrice</th>
                      <th>COUNTRY</th>
                      <th style="width: 10%;">DETAILS</th>
                  </tr>
                  </thead>
                  <tbody>
                  <%
                      ArrayList<Footballers> footballers = (ArrayList<Footballers>)request.getAttribute("items");
                      if(footballers!=null){
                          for(Footballers it : footballers){
                  %>
                  <tr>
                      <td>
                          <%=it.getId()%>
                      </td>
                      <td>
                          <%=it.getName()%>
                      </td>
                      <td>
                          <%=it.getSurname()%>
                      </td>
                      <td>
                          <%=it.getSalary()%>
                      </td>
                      <td>
                          <%=it.getClub()%>
                      </td>
                      <td>
                          <%=it.getTransferPrice()%>
                      </td>
                      <td>
                          <%=it.getYear().getName() + " / " + it.getYear().getShort_Name()%>
                      </td>
                      <td>
                          <a href="/details?id=<%=it.getId()%>" class="btn btn-info btn-sm">DETAILS</a>
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
