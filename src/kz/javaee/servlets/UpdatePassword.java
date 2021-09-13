package kz.javaee.servlets;

import kz.javaee.db.DBManager;
import kz.javaee.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/updatepassword")
public class UpdatePassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("CURRENT_USER");

        if (users!=null) {
            String oldPass = request.getParameter("old_password");
            String newPass = request.getParameter("new_password");
            String rePass = request.getParameter("re_password");

            if(oldPass.equals(users.getPassword())&&newPass.equals(rePass)){

                users.setPassword(newPass);
                DBManager.savePassword(users);

                response.sendRedirect("/profile?passwordsuccess");


            }else {

                response.sendRedirect("/profile?error");


            }

        }else {
            response.sendRedirect("/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
