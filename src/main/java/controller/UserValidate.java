
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

        User user = createUser(request, response);

        initDataBaseConnector();
        
        DataBaseConnector connector = (DataBaseConnector) getServletContext().getAttribute("connector");

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

        //response.sendRedirect("/Calendar/YearPrep");
        String page = "/Calendar/YearPrep";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request,response);
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
        
        try (InputStream input = servletContext
                .getResourceAsStream("WEB-INF/properties/DBconfig.properties");) {

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
