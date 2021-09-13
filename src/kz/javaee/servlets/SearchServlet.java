package kz.javaee.servlets;

import kz.javaee.db.Countries;
import kz.javaee.db.DBManager;
import kz.javaee.db.Footballers;
import kz.javaee.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        if(key==null){
            key = "";
        }
        Long countryId = 0L;
        try {
            countryId = Long.parseLong(request.getParameter("country_id"));
        }catch (Exception e){

        }

        double priceFrom = -1;
        double priceTo = -1;

        try{
            priceFrom = Double.parseDouble(request.getParameter("from_price"));
        }catch (Exception e){

        }

        try{
            priceTo = Double.parseDouble(request.getParameter("to_price"));
        }catch (Exception e){

        }

        ArrayList<Footballers> items = DBManager.searchFootballer(key, countryId, priceFrom, priceTo);
        request.setAttribute("items", items);

        ArrayList<Countries> countries = DBManager.getCountries();
        request.setAttribute("countries", countries);

        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("CURRENT_USER");

        if(users!=null){
            request.setAttribute("currentUser", users);
        }

        request.getRequestDispatcher("/search.jsp").forward(request, response);


    }
}
