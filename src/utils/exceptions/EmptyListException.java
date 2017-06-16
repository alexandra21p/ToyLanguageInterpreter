package utils.exceptions;

/**
 * Created by Alexandra on 16/11/2015.
 */
public class EmptyListException extends Exception  {
    public EmptyListException(String message) {
        super(message);
    }

    public EmptyListException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
