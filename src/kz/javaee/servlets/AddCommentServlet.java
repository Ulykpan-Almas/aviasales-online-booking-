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

@WebServlet(value = "/addcomment")
public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF8");

        String redirect = "/login";
        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("CURRENT_USER");

        if (users!=null) {

            redirect = "/";

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
                    redirect = "/readblog?id="+blogid+"#commentDiv";
                }
            }
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
