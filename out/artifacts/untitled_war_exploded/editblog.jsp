<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.javaee.db.Footballers" %>
<%@ page import="kz.javaee.db.Blogs" %><%--
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
<body>
  <%@include file="wendor/navbar.jsp"%>
   <div class="container">
       <div class="row mt-5">
           <div class="col-sm-12">
               <%
                   String success = request.getParameter("success");
                   if(success!=null){
               %>
               <div class="alert alert-success alert-dismissible fade show" role="alert">
                   Blog added
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
               <%
                   }
               %>
               <%
                   Blogs blog = (Blogs)request.getAttribute("blog");
                       if(blog!=null){
               %>
               <form action="/editblog" method="post">
                   <input type="hidden" name="id" value="<%=blog.getId()%>">
                   <div class="form-group">
                       <label>Title: </label>
                       <input type="text" class="form-control" name="title" value="<%=blog.getTitle()%>">
                   </div>
                   <div class="form-group">
                       <label>Short Content: </label>
                       <textarea class="form-control" name="short_content" rows="5"><%=blog.getShortContent()%></textarea>
                   </div>
                   <div class="form-group">
                       <label>Content: </label>
                       <textarea class="form-control" name="content" rows="10"><%=blog.getContent()%></textarea>
                   </div>
                   <div class="form-group">
                       <button class="btn btn-success">Save</button>
                       <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteBlogModal">
                           Delete
                       </button>
                   </div>
               </form>



               <!-- Modal -->
               <div class="modal fade" id="deleteBlogModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                   <div class="modal-dialog">
                       <div class="modal-content">
                           <form action="/deleteblog" method="post">
                               <input type="hidden" name="id" value="<%=blog.getId()%>">
                               <div class="modal-header">
                               <h5 class="modal-title" id="staticBackdropLabel">Delete BLOG</h5>
                               <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                   <span aria-hidden="true">&times;</span>
                               </button>
                           </div>
                           <div class="modal-body">
                               Are you Sure?
                           </div>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-secondary" data-dismiss="modal">NO</button>
                               <button  class="btn btn-primary">YES</button>
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
