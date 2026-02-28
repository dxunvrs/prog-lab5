package exceptions;


/**
 * Проверяемое исключение для ненайденного id
 */
public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message) {
        super(message);
    }
}
