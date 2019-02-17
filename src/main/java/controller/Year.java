
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnector.DataBaseConnector;
import exceptions.UserIsNotExistException;
import model.Day;
import model.User;
import view.HtmlTableYearCreator;

/**
 * Servlet implementation class Year
 */
@WebServlet("/YearPrep")
public class Year extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Year() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/Year");
       
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("index.html");
            return;
        }

        DataBaseConnector connector = (DataBaseConnector) getServletContext().getAttribute("connector");

        Calendar date = new GregorianCalendar();
        Day day = new Day();
        day.setDate(date);

        redirectToYear(connector, user, day, response);
    }

    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    
    private void redirectToYear(DataBaseConnector connector, User user,
            Day day, HttpServletResponse response) {
        try {
            Map<Calendar, Map<Integer, Day>> year =
                    connector.selectYear(user, day.getDate());

            HtmlTableYearCreator tableCreator = new HtmlTableYearCreator();

            this.getServletContext().setAttribute("year_useful", tableCreator
                    .getYearTable(new HashMap<Calendar, Map<Integer, Day>>(year), "useful"));
            this.getServletContext().setAttribute("year_work", tableCreator
                    .getYearTable(new HashMap<Calendar, Map<Integer, Day>>(year), "work"));
            this.getServletContext().setAttribute("year_study", tableCreator
                    .getYearTable(new HashMap<Calendar, Map<Integer, Day>>(year), "study"));
            this.getServletContext().setAttribute("year_learn_language",
                    tableCreator.getYearTable(new HashMap<Calendar, Map<Integer, Day>>(year),
                            "learn_language"));
            this.getServletContext().setAttribute("year_sport", tableCreator
                    .getYearTable(new HashMap<Calendar, Map<Integer, Day>>(year), "sport"));
            this.getServletContext().setAttribute("year_alcohol",
                    tableCreator.getYearTable(new HashMap<Calendar, Map<Integer, Day>>(year),
                            "alcohol"));
            this.getServletContext().setAttribute("year_smoke", tableCreator
                    .getYearTable(new HashMap<Calendar, Map<Integer, Day>>(year), "smoke"));

            response.sendRedirect("/Calendar/MonthPrep.jsp");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException | UserIsNotExistException e) {
            e.printStackTrace();
        }
    }

}
