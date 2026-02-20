package utility;

/**
 * Интерфейс валидатора на произвольный тип
 * @param <T> - дженерик
 */
public interface Validator<T> {
    /**
     * Получает значение заданного типа, если удовлетворяет условиям - true, нет - false
     */
    boolean validate(T value);
}
