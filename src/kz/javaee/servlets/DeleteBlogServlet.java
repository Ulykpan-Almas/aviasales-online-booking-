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

@WebServlet(value = "/deleteblog")
public class DeleteBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF8");

        String redirect = "/login";
        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("CURRENT_USER");

        if (users!=null) {

            redirect = "/";

            Long blogid = 0L;
            try {
                blogid = Long.parseLong(request.getParameter("id"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Blogs blog = DBManager.getBlog(blogid);

            if(blog!=null && blog.getUser().getId()==users.getId())  {



                DBManager.deleteBlog(blog);
            }
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
