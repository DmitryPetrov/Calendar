
package model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.*;

import exceptions.StringContaintsScriptException;
import model.User;

public class UserTest {

    @Test//(expected = StringContaintsScriptException.class)
    public void constructor_JavaScriptIntoLogin_Exception() {
        try {
            User user = new User("login blablabla <script> * </script>",
                    "password");
        } catch (StringContaintsScriptException e) {
            assertTrue("Login String containts script", true);
            e.printStackTrace();
        }
    }

    @Test//(expected = StringContaintsScriptException.class)
    public void constructor_SqlIntoLogin_Exception() {
        try {
            User user = new User("login blablabla select * from users;",
                    "password");
        } catch (StringContaintsScriptException e) {
            assertTrue("Login String containts SQL comand", true);
            e.printStackTrace();
        }
    }

    @Test//(expected = StringContaintsScriptException.class)
    public void constructor_JavaScriptIntoPaassword_Exception() {
        try {
            User user = new User("login",
                    "password blablabla <script> * </script>");
        } catch (StringContaintsScriptException e) {
            assertTrue("Password String containts script", true);
            e.printStackTrace();
        }
    }

    @Test//(expected = StringContaintsScriptException.class)
    public void constructor_SqlIntoPassword_Exception() {
        try {
            User user = new User("login",
                    "password blablabla select * from users;");
        } catch (StringContaintsScriptException e) {
            assertTrue("Login String containts SQL comand", true);
            e.printStackTrace();
        }
    }

    @Test
    public void constructor_LoginPassword_UserObject() {
        String login = "my login";
        String password = "my password";

        User user = null;
        try {
            user = new User(login, password);
        } catch (StringContaintsScriptException e) {
            e.printStackTrace();
            fail("cant create User obj with tis login:" + login
                    + " and password:" + password);
        } 
    }
}
