package utility;

import exceptions.EndOfInputException;
import exceptions.ScriptExecutionException;
import exceptions.TypeNotFoundException;
import io.UserInput;
import models.UnitOfMeasure;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

/**
 * Класс для формирования ввода значений пользователя, либо из скрипта
 */
public class Form {
    private final UserInput reader;
    /**
     * Режим работы
     */
    private final boolean scriptMode;

    public Form(UserInput reader, boolean scriptMode) {
        this.reader = reader;
        this.scriptMode = scriptMode;
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
        T result = null;
        while (true) {
            try {
                result = map(type, reader.readNextLine("Введите " + name + ": "));
                if (validator.validate(result)) {
                    if (scriptMode) System.out.println(result);
                    break;
                }
            } catch (EndOfInputException e) {
                if (scriptMode) throw new ScriptExecutionException("Получен конец ввода, ожидались данные типа " + type.getSimpleName());
                System.out.println(e.getMessage());
                break;
            } catch (NoSuchElementException e) {
                if (scriptMode) throw new ScriptExecutionException("Получен конец ввода, ожидались данные типа " + type.getSimpleName());
                System.out.println("Конец ввода");
            } catch (NumberFormatException e) {
                if (scriptMode) throw new ScriptExecutionException("Ожидались данные типа " + type.getSimpleName());
                System.out.println("Введите данные типа " + type.getSimpleName());
            } catch (IllegalArgumentException e) {
                if (scriptMode) throw new ScriptExecutionException("Такой единицы измерения не существует");
                System.out.println("Такой единицы измерения не существует");
            } catch (DateTimeParseException e) {
                if (scriptMode) throw new ScriptExecutionException("Некорректная дата (надо yyyy-mm-dd)");
                System.out.println("Введите корректную дату в формате yyyy-mm-dd");
            } catch (TypeNotFoundException e) {
                if (scriptMode) throw new ScriptExecutionException("Тип не поддерживается");
                System.out.println(e.getMessage());
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

        switch (type.getSimpleName()) {
            case "Integer", "int" -> {
                return type.cast(Integer.parseInt(value));
            }
            case "Long", "long" -> {
                return type.cast(Long.parseLong(value));
            }
            case "String" -> {
                return type.cast(value);
            }
            case "LocalDate" -> {
                return type.cast(LocalDate.parse(value));
            }
            case "UnitOfMeasure" -> {
                return type.cast(UnitOfMeasure.valueOf(value));
            }
            default -> {
                throw new TypeNotFoundException("Тип еще не поддерживается");
            }
        }
    }
}