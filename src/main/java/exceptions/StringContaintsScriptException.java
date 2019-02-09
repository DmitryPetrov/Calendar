package exceptions;

public class StringContaintsScriptException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String excMsg;
	
	public StringContaintsScriptException() {
		excMsg = "\nStringContaintsScript";
	}
	
	public StringContaintsScriptException(String message) {
		excMsg = "\nStringContaintsScript " + "\n    " + message;
	}

	public String toString() {
		return "excMsg";
	}
}
