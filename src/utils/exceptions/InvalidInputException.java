package utils.exceptions;

/**
 * Created by Alexandra on 16/11/2015.
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
