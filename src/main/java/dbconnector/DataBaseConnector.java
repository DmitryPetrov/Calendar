package dbconnector;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map;

import exceptions.UserIsNotExistException;
import model.*;

public interface DataBaseConnector {

    public boolean checkDataBaseUser(User user) throws SQLException;
    
    public void selectUser(User user);

    public boolean checkDataBaseDay(User user, Day day)
            throws SQLException, UserIsNotExistException;
    
    public void selectDay(User user, Day day);

    public void insertUser(User user);
    
    public void updateUser(User user);

    public void insertDay(User user, Day day);

    public void updateDay(User user, Day day);

    public Map<Integer, Day> selectMonth(User user, Calendar month)
            throws UserIsNotExistException, SQLException;
}
