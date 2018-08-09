package be.kdg.ip2.carpoolingapplication.services.exceptions;

public class CustomAuthenticationException extends RuntimeException
{
    private Throwable throwable;

    public CustomAuthenticationException(String message)
    {
        super(message);
    }

    public CustomAuthenticationException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
