package dbconnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;

import exceptions.UserIsNotExistException;
import model.*;


public class ConnectorMySql implements DataBaseConnector{

    private static BasicDataSource dataSource;
    
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    
    public ConnectorMySql() {
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://localhost/mood_calendar?serverTimezone=Europe/Moscow&useSSL=false&";
        username = "user";
        password = "password";
        
        dataSource = ConnectorMySql.getDataSource();
    }
    
    public ConnectorMySql(Map<String, String> dbProperties) {
        driver = dbProperties.remove("driver");
        url = dbProperties.remove("url");
        username = dbProperties.remove("username");
        password = dbProperties.remove("password");
        
        dataSource = ConnectorMySql.getDataSource();
    }
    
    
    private static BasicDataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(url);
            ds.setUsername(username);
            ds.setPassword(password);

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);
 
            dataSource = ds;
        }
        return dataSource;
    }    
    
    
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    public void closeConnections() {
        try {
            dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public boolean checkDataBaseUser(User user) throws SQLException {
        try {
            Class.forName(driver).getDeclaredConstructor()
                    .newInstance();

            try (Connection conn = getConnection();
                    Statement statement = conn.createStatement();) {

                String sqlComand = "SELECT * FROM users WHERE login='"
                        + user.getLogin() + "'" + " AND pass_word='"
                        + user.getPassword() + "';";
                
                System.out.println("\n" + "CHECK: " + sqlComand);                

                ResultSet resultSet = statement.executeQuery(sqlComand);
                
                if (resultSet.next()) {
                    return true;
                }       
            }
        } catch (Exception e) {
            System.out.println("\n" + "Connection failed \n" + e);
        }       
        return false;
    }
      

    @Override
    public void selectUser(User user) {
        try {
            Class.forName(driver).getDeclaredConstructor()
                    .newInstance();

            try (Connection conn = getConnection();
                    Statement statement = conn.createStatement();) {

                String sqlComand = "SELECT id FROM users WHERE login='"
                        + user.getLogin() + "'" + " AND pass_word='"
                        + user.getPassword() + "';";
                
                System.out.println("\n" + sqlComand);                

                ResultSet resultSet = statement.executeQuery(sqlComand);
                
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                }       
            }
        } catch (Exception e) {
            System.out.println("\n" + "Connection failed \n" + e);
        }       
    }
    

    @Override
    public boolean checkDataBaseDay(User user, Day day) {
        try {
            Class.forName(driver).getDeclaredConstructor()
                    .newInstance();

            try (Connection conn = getConnection();
                    Statement statement = conn.createStatement();) {

                String sqlComand = "SELECT * FROM days "
                        + "WHERE id=" + user.getId() + " " 
                        + "AND day='" + day.getDateString() + "';";
                
                System.out.println("\n" + "CHECK: " + sqlComand);
                
                ResultSet resultSet = statement.executeQuery(sqlComand);
                
                if (resultSet.next()) {
                    return true;
                }       
            }
        } catch (Exception e) {
            System.out.println("\n" + "Connection failed \n" + e);
        }       
        return false;
    }
    

    @Override
    public void selectDay(User user, Day day) {
        try {
            Class.forName(driver).getDeclaredConstructor()
                    .newInstance();

            try (Connection conn = getConnection();
                    Statement statement = conn.createStatement();) {

                String sqlComand = "SELECT * FROM days "
                        + "WHERE id=" + user.getId() + " " 
                        + "AND day='" + day.getDateString() + "';";
                
                System.out.println("\n" + sqlComand);
                
                ResultSet resultSet = statement.executeQuery(sqlComand);
                
                if (resultSet.next()) {
                    day.setMood(resultSet.getString(3));
                    day.setUseful(resultSet.getInt(4) != 0);
                    day.setWork(resultSet.getInt(5) != 0);
                    day.setStudy(resultSet.getInt(6) != 0);
                    day.setLearnLanguag(resultSet.getInt(7) != 0);
                    day.setSport(resultSet.getInt(8) != 0);
                    day.setAlcohol(resultSet.getInt(9) != 0);
                    day.setSmoke(resultSet.getInt(10) != 0);
                }       
            }
        } catch (Exception e) {
            System.out.println("\n" + "Connection failed \n" + e);
        }       
    }
    

    @Override
    public void insertDay(User user, Day day) {
        String sqlComand = "INSERT days(id, day, mood, useful, worked, study, "
                + "learn_lang, sport, alcohol, smoke) VALUES ("
                + user.getId() + "," + day.dayToStringForDataBase() + ");";
        
        System.out.println("\n" + sqlComand);
        
        comandExecute(sqlComand);  
    }
    

    @Override
    public void updateDay(User user, Day day) {
        String sqlComand = "UPDATE days "
                            + "SET mood='"+ day.getMoodString().toLowerCase() + "', "
                                + "useful=" + day.getUsefulInt() + ", "
                                + "worked=" + day.getWorkInt() + ", "
                                + "study=" + day.getStudyInt() + ", "
                                + "learn_lang=" + day.getLearnLanguagInt() + ", "
                                + "sport=" + day.getSportInt() + ", "
                                + "alcohol=" + day.getAlcoholInt() + ", "
                                + "smoke=" + day.getSmokeInt() + " "
                            + "WHERE id=" + user.getId() + " "
                                    + "AND day='" + day.getDateString() + "';";
        
        System.out.println("\n" + sqlComand);
        
        comandExecute(sqlComand);     
    }    
    

    private int comandExecute(String sqlComand) {
        int stringChanged = -1;
        try {
            Class.forName(driver).getDeclaredConstructor()
                    .newInstance();

            try (Connection conn = getConnection();
                    Statement statement = conn.createStatement();) {

                stringChanged = statement.executeUpdate(sqlComand);

                //System.out.println("\n" + resultSet);
            }
        } catch (Exception e) {
            System.out.println("\n" + "Connection failed \n" + e);
        }
        
        return stringChanged;
    }


    @Override
    public void insertUser(User user) {
        String sqlComand = "INSERT users(login, pass_word, privacy_policy) "
                + "VALUES ('" + user.getLogin() + "', '"
                + user.getPassword() + "', '"
                + user.getPrivatePolice() + "');";
        
        System.out.println("\n" + sqlComand);
        
        comandExecute(sqlComand);  
    }


    @Override
    public void updateUser(User user) {
        String sqlComand = "UPDATE users "
                + "SET login='"+ user.getLogin() + "', "
                    + "pass_word='" + user.getPassword() + "', "
                    + "privacy_policy='" + user.getPrivatePolice() + "' "
                + "WHERE id=" + user.getId() + ";";

    System.out.println("\n" + sqlComand);
    
    comandExecute(sqlComand); 
    }
    

    @Override
    public Map<Integer, Day> selectMonth(User user, Calendar month) {       
        Map<Integer, Day> monthMap = new HashMap<Integer, Day>();
        
        try {
            Class.forName(driver).getDeclaredConstructor()
                    .newInstance();

            try (Connection conn = getConnection();
                    Statement statement = conn.createStatement();) {
                
                String yearMonth = month.get(Calendar.YEAR) + "-0" 
                        + (1 + month.get(Calendar.MONTH)) + "-%";

                String sqlComand = "SELECT * FROM days "
                        + "WHERE id=" + user.getId() + " " 
                        + "AND day LIKE '" + yearMonth + "';";
                
                System.out.println("\n" + sqlComand);                

                ResultSet resultSet = statement.executeQuery(sqlComand);
                
                while(resultSet.next()) {
                    Calendar date = fromStringToCalendar(resultSet.getString(2));
                    
                    Day day = new Day(date);

                    day.setMood(resultSet.getString(3));
                    day.setUseful(resultSet.getInt(4) != 0);
                    day.setWork(resultSet.getInt(5) != 0);
                    day.setStudy(resultSet.getInt(6) != 0);
                    day.setLearnLanguag(resultSet.getInt(7) != 0);
                    day.setSport(resultSet.getInt(8) != 0);
                    day.setAlcohol(resultSet.getInt(9) != 0);
                    day.setSmoke(resultSet.getInt(10) != 0);
                    
                    monthMap.put(date.get(Calendar.DAY_OF_MONTH), day);
                }       
            }
        } catch (Exception e) {
            System.out.println("\n" + "Connection failed \n" + e);
        }       
        return monthMap;
    }


    private Calendar fromStringToCalendar(String str) {
        String[] dateTime = str.split(" ");
        String[] date = dateTime[0].split("-");
        
        int year = Integer.valueOf(date[0]);
        int month = (Integer.valueOf(date[1]) - 1);
        int day = Integer.valueOf(date[2]);
 
        return new GregorianCalendar(year, month, day);
    }


    @Override
    public Map<Calendar, Map<Integer, Day>> selectYear(User user, Calendar year)
            throws UserIsNotExistException, SQLException {
        
        Map<Calendar, Map<Integer, Day>> yearMap = new HashMap<Calendar, Map<Integer, Day>>();
        for(int i = 0; i < 12; i++) {
            Calendar month = new GregorianCalendar(year.get(Calendar.YEAR), i, 1);
            Map<Integer, Day> monthMap = selectMonth(user, month);
            yearMap.put(month, monthMap);
        }
        
        return yearMap;
    }
}
