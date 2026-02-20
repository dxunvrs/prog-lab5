package exceptions;

/**
 * Непроверяемое исключение для ошибки выполнения скрипта
 */
public class ScriptExecutionException extends RuntimeException {
    public ScriptExecutionException(String message) {
        super(message);
    }
}
