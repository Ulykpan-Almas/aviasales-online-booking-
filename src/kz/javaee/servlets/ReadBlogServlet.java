package kz.javaee.servlets;

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
import java.util.ArrayList;

@WebServlet(value = "/readblog")
public class ReadBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("CURRENT_USER");

        if (users != null) {
            request.setAttribute("currentUser", users);
        }
        Long blogid = 0L;
        try {
            blogid = Long.parseLong(request.getParameter("id"));
        }catch (Exception e){
            e.printStackTrace();
        }

        Blogs blog = DBManager.getBlog(blogid);
        if (blog!=null) {
            request.setAttribute("blog", blog);
            ArrayList<Comments> comments = DBManager.getCommentsByBlogId(blogid);
            request.setAttribute("comments",comments);
        }
        request.getRequestDispatcher("/readblog.jsp").forward(request,response);
    }

}
