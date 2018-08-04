package be.kdg.ip2.carpoolers.backend.service.exception;

public class InputValidationException extends RuntimeException {

    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputValidationException(String message) {
        super(message);
    }
}
