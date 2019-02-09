package model;
import exceptions.StringContaintsScriptException;
import exceptions.UserIsNotExistException;

public class User {
    private int id;
	private String login;
	private String password;
	private String privatePolice;

	User() throws UserIsNotExistException {
		throw new UserIsNotExistException("EMPTY CONSTUCTOR: "
				+ "constuctor mast containts login and password");
	}
	
	User(int id, String login, String password, String privatePolice) throws StringContaintsScriptException {
        this(login, password, privatePolice);
        setId(id);
	}

	User(String login, String password, String privatePolice) throws StringContaintsScriptException {
	    this(login, password);
	    
	    if (isScript(privatePolice)) {
            throw new StringContaintsScriptException(
                    "Password Containts Script: " + privatePolice);
        } else {
            setPrivatePolice(privatePolice);
        }
	}
	
	public User(String login, String password) throws StringContaintsScriptException {
		if (isScript(login)) {
			throw new StringContaintsScriptException(
					"Login Containts Script: " + login);
		} else {
			setLogin(login);
		}

		if (isScript(password)) {
			throw new StringContaintsScriptException(
					"Password Containts Script: " + password);
		} else {
			setPassword(password);
		}
		
		setPrivatePolice("positive");
	}

	private boolean isScript(String str) {
		if((str.contains("<script")) 
		        || (str.contains("select") & str.contains("from")) ) {
		    return true;
		}
	    return false;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
	private void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
        return this.login;
    }
	
	private void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

    public String getPrivatePolice() {
        return privatePolice;
    }

    public void setPrivatePolice(String privatePolice) {
        this.privatePolice = privatePolice;
    }
    
    public String toString() {
        String user = "User: id=" + id + ";"
                    + " login=" + login + ";"
                    + " password=" + password + ";"
                    + " privatePolice=" + privatePolice;
        
        return user;
    }
}
