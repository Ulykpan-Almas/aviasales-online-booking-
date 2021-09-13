package kz.javaee.servlets;

import kz.javaee.db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/search2")
public class Search2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        if(key==null){
            key = "";
        }

        String to = request.getParameter("to");
        if(to==null){
            to = "";
        }





        ArrayList<Airlines> airlines = DBManager.searchAir(key,to);
        request.setAttribute("air", airlines);

        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("CURRENT_USER");

        if(users!=null){
            request.setAttribute("currentUser", users);
        }

        request.getRequestDispatcher("/findair.jsp").forward(request, response);


    }
}
