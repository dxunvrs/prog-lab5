package exceptions;

/**
 * Непроверяемое исключение для конца ввода
 */
public class EndOfInputException extends RuntimeException {
    public EndOfInputException(String message) {
        super(message);
    }
}
