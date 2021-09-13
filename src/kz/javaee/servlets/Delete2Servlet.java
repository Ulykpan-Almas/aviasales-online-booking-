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

@WebServlet(value = "/delete2")
public class Delete2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Airlines airlines= DBManager.getairline(id);

        if(airlines!=null){

            if(DBManager.deleteair(airlines)){
                response.sendRedirect("/home2");
            }else {
                response.sendRedirect("/edit2?id="+id+"&error");
            }
        }else {
            response.sendRedirect("/");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
