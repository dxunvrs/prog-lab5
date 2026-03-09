package exceptions;

/**
 * Непроверяемое исключение для завершения работы программы
 */
public class EndOfExecutionException extends RuntimeException {
    public EndOfExecutionException(String message) {
        super(message);
    }
}
