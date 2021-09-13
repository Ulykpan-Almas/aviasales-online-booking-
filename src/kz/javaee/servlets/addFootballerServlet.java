package kz.javaee.servlets;

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

@WebServlet(value = "/addFootballer")
public class addFootballerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String name = request.getParameter("name");
           String Surname = request.getParameter("Surname");
           int Salary=Integer.parseInt(request.getParameter("Salary"));
           String Club = request.getParameter("Club");
           int Transfer=Integer.parseInt(request.getParameter("Transfer"));
           Long countryid = Long.parseLong(request.getParameter("year"));

           Countries cnt = DBManager.getCountry(countryid);
           if(cnt!=null){
                Footballers fb=new Footballers(null,name,Surname,Salary,Club,Transfer, cnt);
                DBManager.addfootballer(fb);
               response.sendRedirect("/addFootballer?success");

           }else {
               response.sendRedirect("/addFootballer?error");

           }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Countries> countries = DBManager.getCountries();
        request.setAttribute("countries",countries);

        request.getRequestDispatcher("/addFootballer.jsp").forward(request,response);
    }
}
