package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dbconnector.ConnectorMySql;
import dbconnector.DataBaseConnector;

/**
 * Application Lifecycle Listener implementation class DataBaseConnection
 *
 */
public class DataBaseConnection implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DataBaseConnection() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        initDataBaseConnector(sce);
    }
    
    private void initDataBaseConnector(ServletContextEvent event) {
        Map<String, String> dbProperties = readDataBaseProperties(event);

        DataBaseConnector connector = new ConnectorMySql(dbProperties);

        ServletContext servletContext = event.getServletContext();
        servletContext.setAttribute("connector", connector);
    }

    private Map<String, String> readDataBaseProperties(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();

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
	
}
