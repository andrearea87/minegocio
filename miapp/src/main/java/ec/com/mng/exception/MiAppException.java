package ec.com.mng.exception;

import java.util.List;

/**
 * 
 * @author
 *
 */
public class MiAppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> subErrors;
	/**
     * Constructor.
     */
    public MiAppException() {
        super();
    }

    /**
     * Constructor with args.
     *
     * @param message            The message
     * @param cause              The cause
     * @param enableSuppression  true if want to enable suppression, false if not.
     * @param writableStackTrace true if are writable the stack trace, false if not.
     */
    public MiAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message,
              cause,
              enableSuppression,
              writableStackTrace);
    }

    /**
     * Constructor with args.
     *
     * @param message The message
     * @param cause   The cause
     */
    public MiAppException(String message, Throwable cause) {
        super(message,
              cause);
    }

    /**
     * Constructor with args. Personalize exception for ADJ.
     *
     * @param msg A message for exception
     * @param subErrors a list with details of error
     */
    public MiAppException(String msg, List<String> subErrors) {
        super(msg);
        this.subErrors = subErrors;
    }
    
    /**
     * Constructor with args. Personalize exception for ADJ.
     *
     * @param msg A message for exception
     */
    public MiAppException(String msg) {
        super(msg);
    }


    /**
     * Constructor with args.
     *
     * @param cause The cause
     */
    public MiAppException(Throwable cause) {
        super(cause);
    }

    
	public List<String> getSubErrors() {
		return subErrors;
	}
    
    
}
