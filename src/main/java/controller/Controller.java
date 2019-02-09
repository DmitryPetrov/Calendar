
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
import view.HtmlTableCreator;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/Controller");
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("index.html");
            return;
        }

        DataBaseConnector connector =
                (DataBaseConnector) session.getAttribute("connector");

        Calendar date = new GregorianCalendar();
        Day day = new Day();
        day.setDate(date);

        redirectToMonth(connector, user, day, response);
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

    private void redirectToMonth(DataBaseConnector connector, User user,
            Day day, HttpServletResponse response) {
        try {
            Map<Integer, Day> month =
                    connector.selectMonth(user, day.getDate());

            HtmlTableCreator tableCreator = new HtmlTableCreator();

            this.getServletContext().setAttribute("useful",
                    tableCreator.getMonthTable(new HashMap<Integer, Day>(month), "useful"));
            this.getServletContext().setAttribute("work",
                    tableCreator.getMonthTable(new HashMap<Integer, Day>(month), "work"));
            this.getServletContext().setAttribute("study",
                    tableCreator.getMonthTable(new HashMap<Integer, Day>(month), "study"));
            this.getServletContext().setAttribute("learn_language",
                    tableCreator.getMonthTable(new HashMap<Integer, Day>(month), "learn_language"));
            this.getServletContext().setAttribute("sport",
                    tableCreator.getMonthTable(new HashMap<Integer, Day>(month), "sport"));
            this.getServletContext().setAttribute("alcohol",
                    tableCreator.getMonthTable(new HashMap<Integer, Day>(month), "alcohol"));
            this.getServletContext().setAttribute("smoke",
                    tableCreator.getMonthTable(new HashMap<Integer, Day>(month), "smoke"));

            response.sendRedirect("month.jsp");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException | UserIsNotExistException e) {
            e.printStackTrace();
        }
    }

}
