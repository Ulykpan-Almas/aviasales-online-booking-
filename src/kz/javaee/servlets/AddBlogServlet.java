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

@WebServlet(value = "/addblog")
public class AddBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF8");
        String redirect = "/login";
        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("CURRENT_USER");

        if (users!=null) {
            redirect = "/";
            String title = request.getParameter("title");
            String shortContent = request.getParameter("short_content");
            String content = request.getParameter("content");

            Blogs blog = new Blogs(null,users,title,shortContent,content,null);
            if (DBManager.addBlog(blog)){
                redirect = "/addblog?success";
            }
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("CURRENT_USER");

        if (users!=null) {
            request.setAttribute("currentUser",users);
            request.getRequestDispatcher("/addblog.jsp").forward(request,response);
        }
    }
}
