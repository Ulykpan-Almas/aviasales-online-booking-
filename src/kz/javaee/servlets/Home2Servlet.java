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
import java.util.ArrayList;

@WebServlet(value = "/home2")
public class Home2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Airlines> airlines = DBManager.getAirlines();
        request.setAttribute("air",airlines);
        request.getRequestDispatcher("/home2.jsp").forward(request, response);


    }
}
