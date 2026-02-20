package exceptions;

public class EndOfInputException extends RuntimeException {
    public EndOfInputException(String message) {
        super(message);
    }
}
