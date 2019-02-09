package exceptions;

public class UserIsNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String excMsg;
	
	public UserIsNotExistException() {
		excMsg = "\nUserIsNotExist";
	}
	
	public UserIsNotExistException(String message) {
		excMsg = "\nUserIsNotExist " + "\n    " + message;
	}

	public String toString() {
		return "excMsg";
	}
}
