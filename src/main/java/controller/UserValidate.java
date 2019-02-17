
package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnector.ConnectorMySql;
import dbconnector.DataBaseConnector;
import exceptions.StringContaintsScriptException;
import model.User;

/**
 * Servlet implementation class UserValidate
 */
@WebServlet("/UserValidate")
public class UserValidate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserValidate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        System.out.println("/UserValidate");

        User user = createUser(request, response);

        DataBaseConnector connector = new ConnectorMySql();

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
        session.setAttribute("connector", connector);

        response.sendRedirect("/Calendar/YearPrep");
    }
    
    private User createUser(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        
        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");
        if((login == null) || (password == null)) {
            response.sendRedirect("index.html");
        }
        
        User user = null;
        try {
            user = new User(login, password);
        } catch (StringContaintsScriptException e) {
            e.printStackTrace();
            response.sendRedirect("index.html");
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
