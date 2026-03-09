package exceptions;

/**
 * Непроверяемое исключение для ошибки выполнения команды save
 */
public class SaveException extends RuntimeException {
    public SaveException(String message) {
        super(message);
    }
}
