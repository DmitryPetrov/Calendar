
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        String page = "/year.jsp";

        if (user == null) {
            page = "/index.html";
        } else {
            createHtmlTables(user, request);
        }

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
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

    private void createHtmlTables(User user, HttpServletRequest request) {

        Map<Calendar, Map<Integer, Day>> year = selectYearFromDataBase(user);

        HtmlTableYearCreator tableCreator = new HtmlTableYearCreator();

        request.setAttribute("year_useful",
                tableCreator.getYearTable(new HashMap<>(year), "useful"));

        request.setAttribute("year_work",
                tableCreator.getYearTable(new HashMap<>(year), "work"));
        
        request.setAttribute("year_study",
                tableCreator.getYearTable(new HashMap<>(year), "study"));

        request.setAttribute("year_learn_language", tableCreator
                .getYearTable(new HashMap<>(year), "learn_language"));

        request.setAttribute("year_sport",
                tableCreator.getYearTable(new HashMap<>(year), "sport"));

        request.setAttribute("year_alcohol",
                tableCreator.getYearTable(new HashMap<>(year), "alcohol"));

        request.setAttribute("year_smoke",
                tableCreator.getYearTable(new HashMap<>(year), "smoke"));
    }

    private Map<Calendar, Map<Integer, Day>> selectYearFromDataBase(User user) {
        DataBaseConnector connector = (DataBaseConnector) getServletContext()
                .getAttribute("connector");

        Calendar date = new GregorianCalendar();
        Day day = new Day();
        day.setDate(date);

        Map<Calendar, Map<Integer, Day>> year;

        try {
            year = connector.selectYear(user, day.getDate());
        } catch (SQLException | UserIsNotExistException e) {
            e.printStackTrace();
            return null;
        }

        return year;
    }

}
