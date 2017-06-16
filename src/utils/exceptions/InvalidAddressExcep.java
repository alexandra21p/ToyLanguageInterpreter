package utils.exceptions;

/**
 * Created by Alexandra on 07/12/2015.
 */
public class InvalidAddressExcep extends Exception {
    public InvalidAddressExcep(String message) {
        super(message);
    }

    public InvalidAddressExcep(String message, Throwable throwable) {
        super(message, throwable);
    }
}
