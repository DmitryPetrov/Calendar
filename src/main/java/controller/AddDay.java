
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

/**
 * Servlet implementation class AddDay
 */
@WebServlet("/AddDay")
public class AddDay extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDay() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        System.out.println("/AddDay");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            String page = "/index.html";
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            insertDayIntoDataBase(request, user);
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void insertDayIntoDataBase(HttpServletRequest request, User user) {

        DataBaseConnector connector = (DataBaseConnector) getServletContext()
                .getAttribute("connector");

        Day day = readDayFromRequest(request);

        try {
            if (connector.checkDataBaseDay(user, day)) {
                connector.updateDay(user, day);
            } else {
                connector.insertDay(user, day);
            }
        } catch (SQLException | UserIsNotExistException e) {
            e.printStackTrace();
        }
    }

    private Day readDayFromRequest(HttpServletRequest request) {
        Day day = new Day();

        Calendar date = readDayFromRequest(request.getParameter("date"));

        day.setDate(date);
        day.setMood(request.getParameter("mood"));

        if (request.getParameter("useful") != null) {
            day.setUseful(true);
        }
        if (request.getParameter("work") != null) {
            day.setWork(true);
        }
        if (request.getParameter("study") != null) {
            day.setStudy(true);
        }
        if (request.getParameter("learnLanguag") != null) {
            day.setLearnLanguag(true);
        }
        if (request.getParameter("sport") != null) {
            day.setSport(true);
        }
        if (request.getParameter("alcohol") != null) {
            day.setAlcohol(true);
        }
        if (request.getParameter("smoke") != null) {
            day.setSmoke(true);
        }

        return day;
    }

    private Calendar readDayFromRequest(String string) {
        String[] date = string.split("-");

        int year = Integer.valueOf(date[0]);
        int month = Integer.valueOf(date[1]);
        int day = Integer.valueOf(date[2]);

        return new GregorianCalendar(year, month, day);
    }

}
