package be.kdg.ip2.carpoolingapplication.services.exceptions;

public class RideServiceException extends Exception {
    public RideServiceException(String message) {
        super(message);
    }

    public RideServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
