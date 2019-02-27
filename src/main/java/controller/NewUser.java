
package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnector.DataBaseConnector;
import exceptions.StringContaintsScriptException;
import model.User;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/NewUser");

        selectUserFromDataBase(request, response);

        String page = "/WeekPrep";
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
    
    private void selectUserFromDataBase(HttpServletRequest request,
            HttpServletResponse response) {

        DataBaseConnector connector = (DataBaseConnector) getServletContext()
                .getAttribute("connector");
        
        User user = createUser(request, response);
        
        try {
            if (connector.checkDataBaseUser(user)) {
                connector.selectUser(user);
            } else {
                connector.insertUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }    

    private User createUser(HttpServletRequest request,
            HttpServletResponse response) {

        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");

        if ((login == null) || (password == null)) {

            String page = "/index.html";
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(page);

            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        User user = null;
        try {
            user = new User(login, password);
        } catch (StringContaintsScriptException e) {
            e.printStackTrace();

            String page = "/index.html";
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(page);

            try {
                dispatcher.forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return user;
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

}
