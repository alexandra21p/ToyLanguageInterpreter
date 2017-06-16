package utils.exceptions;

/**
 * Created by Alexandra on 16/11/2015.
 */
public class EmptyStackExcep extends Exception {

    public EmptyStackExcep(String message) {
        super(message);
    }

    public EmptyStackExcep(String message, Throwable throwable) {
        super(message, throwable);
    }

}
