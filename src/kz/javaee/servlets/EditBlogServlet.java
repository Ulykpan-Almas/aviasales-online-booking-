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

@WebServlet(value = "/editblog")
public class EditBlogServlet extends HttpServlet {
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

                String title = request.getParameter("title");
                String shortContent = request.getParameter("short_content");
                String content = request.getParameter("content");

                blog.setTitle(title);
                blog.setShortContent(shortContent);
                blog.setContent(content);

                if (DBManager.saveBlog(blog)) {
                    redirect = "/readblog?id="+blogid;
                }
            }
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("CURRENT_USER");

        if (users != null) {
            request.setAttribute("currentUser", users);

            Long blogid = 0L;
            try {
                blogid = Long.parseLong(request.getParameter("id"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Blogs blog = DBManager.getBlog(blogid);
            if (blog!=null && blog.getUser().getId()==users.getId()){
                request.setAttribute("blog", blog);
                request.getRequestDispatcher("/editblog.jsp").forward(request, response);
            }else {
                response.sendRedirect("/");
            }

        }else {
            response.sendRedirect("/login");
        }
    }

}
