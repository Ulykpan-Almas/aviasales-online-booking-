package kz.javaee.servlets;

import com.google.gson.Gson;
import kz.javaee.db.Blogs;
import kz.javaee.db.Comments;
import kz.javaee.db.DBManager;
import kz.javaee.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/loadcomments")
public class LoadCommentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF8");

        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("CURRENT_USER");
        String result = "ERROR";

        if (users!=null) {

            Long blogid = 0L;
            try {
                blogid = Long.parseLong(request.getParameter("blog_id"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Blogs blog = DBManager.getBlog(blogid);

            if(blog!=null)  {

                String comm = request.getParameter("comment");

                Comments comment = new Comments(null,users,blog,comm,null);



                if (DBManager.addComment(comment)) {
                  result = "OK";
                }
            }
        }
        PrintWriter out = response.getWriter();
        out.print(result);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long blogid = 0L;
        try {
            blogid = Long.parseLong(request.getParameter("blog_id"));
        }catch (Exception e){
            e.printStackTrace();
        }
            ArrayList<Comments> comments = DBManager.getCommentsByBlogId(blogid);
            Gson gson = new Gson();
            String jsonResult = gson.toJson(comments);

        PrintWriter out = response.getWriter();
        out.print(jsonResult);
        }
    }


