package be.kdg.ip2.carpoolingapplication.services.exceptions;

public class InputValidationException extends RuntimeException {

    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputValidationException(String message) {
        super(message);
    }
}
