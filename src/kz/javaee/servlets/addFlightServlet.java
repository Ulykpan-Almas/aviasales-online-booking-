package kz.javaee.servlets;

import kz.javaee.db.Airlines;
import kz.javaee.db.Countries;
import kz.javaee.db.DBManager;
import kz.javaee.db.Footballers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/addFlight")
public class addFlightServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

           String from = request.getParameter("from");
           String to = request.getParameter("to");
           int when=Integer.parseInt(request.getParameter("when"));
           int passenger =Integer.parseInt(request.getParameter("passenger"));




                Airlines ar=new Airlines(null,from,to,when,passenger);
                DBManager.addAirline(ar);
                response.sendRedirect("/addFlight?success");



    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/addFlight.jsp").forward(request,response);
    }
}
