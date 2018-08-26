package be.kdg.ip2.carpoolingapplication.services.exceptions;

public class RideRequestServiceException extends Exception {
    public RideRequestServiceException(String message) {
        super(message);
    }

    public RideRequestServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
