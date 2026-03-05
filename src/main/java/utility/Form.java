package utility;

import exceptions.EndOfExecutionException;
import exceptions.EndOfInputException;
import exceptions.ScriptExecutionException;
import exceptions.TypeNotFoundException;
import io.InputReader;
import models.UnitOfMeasure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Класс для формирования ввода значений пользователя, либо из скрипта
 */
public class Form {
    private static final Logger logger = LoggerFactory.getLogger(Form.class);
    private final InputReader reader;
    /**
     * Режим работы
     */
    private final boolean scriptMode;

    public Form(InputReader reader) {
        this.reader = reader;
        this.scriptMode = reader.isScriptMode();
    }

    /**
     * Метод запроса одного значения, реализовано дженериком
     * @param type тип значения
     * @param name название запрашиваемой величины
     * @param validator объект, реализующий валидатор для заданного типа
     * @return Значение, удовлетворяющее типу и условиям валидатора. Ввод продолжится, пока не будет валидного значения
     * @param <T> дженерик
     */
    protected <T> T ask(Class<T> type, String name, Validator<T> validator) {
        T result;
        while (true) {
            try {
                logger.info("У пользователя запрашивается {} типа {}", name, type.getSimpleName());
                result = map(type, reader.readNextLine("Введите " + name + ": "));
                if (validator.validate(result)) {
                    if (scriptMode) System.out.println(result);
                    break;
                }
                logger.error("Значение не прошло валидацию");

            } catch (EndOfInputException e) {
                if (scriptMode) throw new ScriptExecutionException("Получен конец ввода, ожидались данные типа " + type.getSimpleName());
                throw new EndOfExecutionException("Конец ввода");

            } catch (NumberFormatException e) {
                handleError("Ожидались данные типа " + type.getSimpleName(), e);
            } catch (IllegalArgumentException e) {
                handleError("Такой единицы измерения не существует", e);
            } catch (DateTimeParseException e) {
                handleError("Ожидалась дата в формате yyyy-MM-dd", e);
            } catch (TypeNotFoundException e) {
                handleError("Тип не поддерживается", e);
            }
        }
        return result;
    }

    /**
     * Метод преобразования {@link String} в заданный тип.
     * Поддерживается: int, long, Int, Long, String, LocalDate, UnitOfMeasure
     * @param type тип значения
     * @param value величина
     * @return Возвращает заданный тип, если этот тип поддерживается
     * @param <T> дженерик
     * @throws TypeNotFoundException исключение для разработчика
     */
    private <T> T map(Class<T> type, String value) throws TypeNotFoundException {
        if (value == null || value.isEmpty()) {
            return null;
        }

        return switch (type.getSimpleName()) {
            case "Integer", "int" -> type.cast(Integer.parseInt(value));
            case "Long", "long" -> type.cast(Long.parseLong(value));
            case "String" -> type.cast(value);
            case "LocalDate" -> type.cast(LocalDate.parse(value));
            case "UnitOfMeasure" -> type.cast(UnitOfMeasure.valueOf(value.toUpperCase()));
            default -> throw new TypeNotFoundException("Тип еще не поддерживается");
        };
    }

    /**
     * Метод для обработки ошибок
     * @param message сообщение ошибки
     * @param e класс ошибки
     */
    private void handleError(String message, Exception e) {
        if (scriptMode) {
            throw new ScriptExecutionException(message);
        }
        logger.error(message, e);
        System.out.println(message);
    }
}