package be.kdg.ip2.carpoolingapplication.services.exceptions;

public class CarServiceException extends RuntimeException{
    public CarServiceException(String message){
        super(message);
    }
    public CarServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
