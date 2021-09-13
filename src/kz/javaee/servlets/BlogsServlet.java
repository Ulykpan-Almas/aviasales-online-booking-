package kz.javaee.servlets;

import kz.javaee.db.Blogs;
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

@WebServlet(value = "/blogs")
public class BlogsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("CURRENT_USER");

        if (users!=null) {
            request.setAttribute("currentUser", users);
        }
        ArrayList <Blogs> blogs = DBManager.getAllBlogs();
        request.setAttribute("blogs",blogs);
        request.getRequestDispatcher("blogs.jsp").forward(request, response);

    }
}
