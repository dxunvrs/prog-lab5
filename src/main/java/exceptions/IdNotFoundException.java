package exceptions;


/**
 * Проверяемое исключение для ненайденного id
 */
public class IdNotFoundException extends Exception {
    public IdNotFoundException(String message) {
        super(message);
    }
}
