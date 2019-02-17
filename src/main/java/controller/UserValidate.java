
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

        initDataBaseConnector();

        selectUserFromDataBase(request, response);

        String page = "/week.html";
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
            HttpServletResponse response){

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

    private void initDataBaseConnector() {
        Map<String, String> dbProperties = readDataBaseProperties();

        DataBaseConnector connector = new ConnectorMySql(dbProperties);

        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("connector", connector);
    }

    private Map<String, String> readDataBaseProperties() {
        ServletContext servletContext = getServletContext();

        String driver = null;
        String url = null;
        String username = null;
        String password = null;

        try (InputStream input = servletContext.getResourceAsStream(
                "WEB-INF/properties/DBconfig.properties");) {

            Properties property = new Properties();
            property.load(input);

            driver = property.getProperty("jdbc.mysql.driver");
            url = property.getProperty("jdbc.mysql.url");
            username = property.getProperty("jdbc.mysql.username");
            password = property.getProperty("jdbc.mysql.password");
        } catch (IOException e) {
            System.out.println("\n" + "Create connection failed \n" + e);
        }

        Map<String, String> dbProperties = new HashMap<String, String>();

        dbProperties.put("driver", driver);
        dbProperties.put("url", url);
        dbProperties.put("username", username);
        dbProperties.put("password", password);

        return dbProperties;
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
