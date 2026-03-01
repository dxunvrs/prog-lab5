package exceptions;

public class ExitException extends RuntimeException {
    public ExitException(String message) {
        super(message);
    }
}
