package kz.javaee.servlets;

import kz.javaee.db.Blogs;
import kz.javaee.db.DBManager;
import kz.javaee.db.Users;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/tolike")
public class ToLikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("CURRENT_USER");

        String output = "ERROR";

        if (users!=null) {
            Long blogid = 0L;
            try {
                blogid = Long.parseLong(request.getParameter("blog_id"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Blogs blog = DBManager.getBlog(blogid);

            if(blog!=null){

               int likes =  DBManager.likeBlog(blog,users);
               output=likes+"";

            }

        }
        PrintWriter out = response.getWriter();
        out.print(output);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
