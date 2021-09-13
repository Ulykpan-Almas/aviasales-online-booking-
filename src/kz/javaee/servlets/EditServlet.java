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

@WebServlet(value = "/edit")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String Surname = request.getParameter("Surname");
        int Salary=Integer.parseInt(request.getParameter("Salary"));
        String Club = request.getParameter("Club");
        int Transfer=Integer.parseInt(request.getParameter("Transfer"));
        Long countryid = Long.parseLong(request.getParameter("year"));

        String redirect="/";

        Countries cnt = DBManager.getCountry(countryid);
        if(cnt!=null) {
            Footballers footballers=DBManager.getfootballer(id);
            if(footballers!=null){
                footballers.setName(name);
                footballers.setSurname(Surname);
                footballers.setSalary(Salary);
                footballers.setClub(Club);
                footballers.setTransferPrice(Transfer);
                footballers.setYear(cnt);

                if(DBManager.savefootballer(footballers)){
                    redirect="/edit?id="+id+"&success";
                }else {
                    redirect="/edit?id="+id+"&error";
                }




            }
        }
           response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id=0L;
        try {
            id = Long.parseLong(request.getParameter("id"));
        }catch (Exception e){
            e.printStackTrace();
        }
        Footballers footballers= DBManager.getfootballer(id);

        if(footballers!=null){
            request.setAttribute("football",footballers);

            ArrayList<Countries> countries = DBManager.getCountries();
            request.setAttribute("countries",countries);

            request.getRequestDispatcher("/edit.jsp").forward(request, response);

        }else {
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
