package exceptions;

public class EndOfExecutionException extends RuntimeException {
    public EndOfExecutionException(String message) {
        super(message);
    }
}
