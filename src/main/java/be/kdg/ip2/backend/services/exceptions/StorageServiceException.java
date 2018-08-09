package be.kdg.ip2.backend.services.exceptions;

public class StorageServiceException extends RuntimeException
{
    public StorageServiceException(String message)
    {
        super(message);
    }
}
