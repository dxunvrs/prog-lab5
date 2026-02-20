package exceptions;

/**
 * Непроверяемое исключение для конца скрипта
 */
public class EndOfInputException extends RuntimeException {
    public EndOfInputException(String message) {
        super(message);
    }
}
