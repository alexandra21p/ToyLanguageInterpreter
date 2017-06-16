package utils.exceptions;

/**
 * Created by Alexandra on 16/11/2015.
 */
public class DivisionByZeroExcep extends Exception {
    public DivisionByZeroExcep(String message) {
        super(message);
    }

    public DivisionByZeroExcep(String message, Throwable throwable) {
        super(message, throwable);
    }
}

