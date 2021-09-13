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
<body onload="LoadComments()">
  <%@include file="wendor/navbar.jsp"%>
   <div class="container">
       <div class="row mt-5">
           <div class="col-sm-12">

               <%
                   Blogs blog = (Blogs) request.getAttribute("blog");
                   if (blog!=null){
               %>

               <div class="jumbotron">
                   <h1><%=blog.getTitle()%></h1>
                   <p class="lead"><%=blog.getShortContent()%></p>
                   <p class="lead"><%=blog.getContent()%></p>
                   <hr class="my-4">
                   <p>Posted by <%=blog.getUser().getFullName()%> at <%=blog.getPostDate()%></p>
                   <p>
                       <table cellpadding="3px">
                           <tr>
                               <td><a href="JavaScript:void(0)" style="color: #ff0000; text-decoration: none;font-size: 22px;" onclick="toLike()">&#9829;</a></td>
                               <td><span style="font-size: 14px;"><span id="like_amount"><%=blog.getLikes()%></span> likes</span></td>
                           </tr>
                       </table>
                   </p>
                   <%
                       if(currentUser!=null){
                   %>
                   <script type="text/javascript">
                       function toLike() {
                          $.post("/tolike",{
                              blog_id: <%=blog.getId()%>
                              },function (result) {
                                   if (result!="ERROR"){

                                       $("#like_amount").html(result);

                                   }
                              });
                       }
                   </script>
                   <%
                       }
                   %>


                   <p>

                       <%
                           if(currentUser!=null && blog.getUser().getId()==currentUser.getId()){
                       %>
                          <a href="/editblog?id=<%=blog.getId()%>" class="btn btn-primary">EDIT</a>
                       <%
                           }
                       %>

                   </p>
               </div>

               <div class="row mt-3" id="commentDiv">
                   <div class="col-12">
                       <h2>Comments</h2>
                       <%
                          if (currentUser!=null){
                       %>

                           <input type="hidden" name="blog_id" value="<%=blog.getId()%>">
                           <div class="form-group">
                               <textarea class="form-control" placeholder="Insert comment" id="comment_text_id"></textarea>
                               <button class="btn btn-success mt-2" onclick="addComment()">Add Comment</button>
                           </div>

                       <script type="text/javascript">
                           function addComment() {
                               $.post("/loadcomments",{
                                   blog_id: <%=blog.getId()%>,
                                   comment: $("#comment_text_id").val()
                               },function (result) {
                                   if(result==="OK"){
                                       $("#comment_text_id").val("");
                                       LoadComments();
                                   }
                               });
                           }
                       </script>

                       <% } %>
                       <div class="list-group" id="comments_id">



                       </div>
                       <script type="text/javascript">

                           function LoadComments() {

                               $.get("/loadcomments",{
                                   blog_id: <%=blog.getId()%>
                               },function (result){

                               var comments = JSON.parse(result)
                               var commentHTML = "";
                               for (i=0;i<comments.length;i++){
                                   commentHTML+="<a href='JavaScript:void(0)' class='list-group-item list-group-item-action'>";
                                   commentHTML+="<div class='d-flex w-100 justify-content-between'>";
                                   commentHTML+="<h5 class='mb-1'>";
                                   commentHTML+=comments[i].user.fullName;
                                   commentHTML+="</h5>";
                                   commentHTML+="<small>"+comments[i].postDate+"</small>";
                                   commentHTML+="</div>";
                                   commentHTML+="<p class='mb-1'>";
                                   commentHTML+=comments[i].comment;
                                   commentHTML+="</p>";
                                   commentHTML+="</a>";
                                   commentHTML+="</div>";

                               }
                               $("#comments_id").html(commentHTML);

                               });

                           }
                       </script>


                   </div>
               </div>



               <%
                   }else {
               %>
                    <h1 class="text-center">404 NOT FOUND</h1>
               <%
                   }
               %>



           </div>
       </div>
   </div>
</body>

<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
</html>
