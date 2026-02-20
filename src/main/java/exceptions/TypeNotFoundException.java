package exceptions;

/**
 * Проверяемое исключения для ненайденного типа.
 * Может возникнуть только по ошибке разработчика, если в {@link utility.Form#ask} запросить недоступный тип
 */
public class TypeNotFoundException extends Exception {
    public TypeNotFoundException(String message) {
        super(message);
    }
}
