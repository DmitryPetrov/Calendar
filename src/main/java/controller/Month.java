
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
import view.HtmlTableCreator;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/MonthPrep")
public class Month extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Month() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/Month");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String page = "/month.jsp";
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

        Map<Integer, Day> month = selectMonthFromDataBase(user);

        HtmlTableCreator tableCreator = new HtmlTableCreator();

        request.setAttribute("month_useful", tableCreator
                .getMonthTable(new HashMap<Integer, Day>(month), "useful"));
        
        request.setAttribute("month_work", tableCreator
                .getMonthTable(new HashMap<Integer, Day>(month), "work"));
        
        request.setAttribute("month_study", tableCreator
                .getMonthTable(new HashMap<Integer, Day>(month), "study"));
        
        request.setAttribute("month_learn_language", tableCreator.getMonthTable(
                new HashMap<Integer, Day>(month), "learn_language"));
        
        request.setAttribute("month_sport", tableCreator
                .getMonthTable(new HashMap<Integer, Day>(month), "sport"));
        
        request.setAttribute("month_alcohol", tableCreator
                .getMonthTable(new HashMap<Integer, Day>(month), "alcohol"));
        
        request.setAttribute("month_smoke", tableCreator
                .getMonthTable(new HashMap<Integer, Day>(month), "smoke"));

    }

    private Map<Integer, Day> selectMonthFromDataBase(User user) {
        DataBaseConnector connector = (DataBaseConnector) getServletContext()
                .getAttribute("connector");

        Calendar date = new GregorianCalendar();
        Day day = new Day();
        day.setDate(date);

        Map<Integer, Day> month;
        try {
            month = connector.selectMonth(user, day.getDate());
        } catch (SQLException | UserIsNotExistException e) {
            e.printStackTrace();
            return null;
        }

        return month;
    }

}
