package be.kdg.ip2.carpoolingapplication.services;

public class CarpoolerServiceException extends RuntimeException {
    public CarpoolerServiceException(String carpooler_not_found) {
        super(carpooler_not_found);
    }
}
