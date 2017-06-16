package utils.exceptions;

/**
 * Created by Alexandra on 16/11/2015.
 */
public class VariableException extends Exception{
    public VariableException(String message) {
        super(message);
    }

    public VariableException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
