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

@WebServlet(value = "/edit2")
public class Edit2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        int when=Integer.parseInt(request.getParameter("when"));
        int pass=Integer.parseInt(request.getParameter("passenger"));


            Airlines airlines=DBManager.getairline(id);
            if(airlines!=null){
                airlines.setFrom_city(from);
                airlines.setTo_city(to);
                airlines.setWhen_flight(when);
                airlines.setPeople_number(pass);

                if(DBManager.saveAir(airlines)){
                    response.sendRedirect("/edit2?id="+id+"&success");
                }else {
                    response.sendRedirect("/edit2?id="+id+"&error");
                }




            }else {
                response.sendRedirect("/details2");
            }
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
            request.setAttribute("air",airlines);

            request.getRequestDispatcher("/edit2.jsp").forward(request, response);

        }else {
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
