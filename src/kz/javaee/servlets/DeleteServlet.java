package kz.javaee.servlets;

import kz.javaee.db.DBManager;
import kz.javaee.db.Footballers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Footballers footballers= DBManager.getfootballer(id);

        if(footballers!=null){

            if(DBManager.deletefootballer(footballers)){
                response.sendRedirect("/");
            }else {
                response.sendRedirect("/edit?id="+id+"&error");
            }
        }else {
            response.sendRedirect("/");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
