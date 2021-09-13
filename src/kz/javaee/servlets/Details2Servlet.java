package kz.javaee.servlets;

import kz.javaee.db.Airlines;
import kz.javaee.db.DBManager;
import kz.javaee.db.Footballers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/details2")
public class Details2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id=0L;
        try {
            id = Long.parseLong(request.getParameter("id"));
        }catch (Exception e){
            e.printStackTrace();
        }
        Airlines airlines= DBManager.getairline(id);

        if(airlines!=null){
            request.setAttribute("airline",airlines);
            request.getRequestDispatcher("/details2.jsp").forward(request, response);

        }else {
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
