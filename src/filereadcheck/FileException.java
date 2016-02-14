package filereadcheck;

public class FileException extends RuntimeException {

	// for serialization
	private static final long serialVersionUID = 1L;

	/**
	 * Create an exception based on an issue in our code.
	 */
	public FileException (String message, Object ... values) {
		super(String.format(message, values));
	}

	/**
	 * Create an exception based on a caught exception with a different message.
	 */
	public FileException (Throwable cause, String message, Object ... values) {
		super(String.format(message, values), cause);
	}

	/**
	 * Create an exception based on a caught exception, with no additional message.
	 */
	public FileException (Throwable exception) {
		super(exception);
	}
}

