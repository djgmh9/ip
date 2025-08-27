package exception;

/**
 * General exception class for Frenny application.
 * This can be used as a base exception for various error scenarios.
 */
public class FrennyException extends Exception {
    public FrennyException(String message) {
        super(message);
    }

    public FrennyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrennyException(Throwable cause) {
        super(cause);
    }
}
