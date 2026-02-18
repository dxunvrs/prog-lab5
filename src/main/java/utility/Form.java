package utility;

import exceptions.TypeNotFoundException;
import io.UserInput;
import models.UnitOfMeasure;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

public class Form {
    private final UserInput reader;

    public Form(UserInput reader) {
        this.reader = reader;
    }

    protected <T> T ask(Class<T> type, String name, Validator<T> validator) {
        T result;
        while (true) {
            try {
                result = map(type, reader.readNextLine("Введите " + name + ": "));
                if (validator.validate(result)) {
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Конец ввода. Завершение программы...");
                System.exit(0);
            } catch (NumberFormatException e) {
                System.out.println("Введите данные типа " + type.getSimpleName());
            } catch (IllegalArgumentException e) {
                System.out.println("Такой единицы измерения не существует");
            } catch (DateTimeParseException e) {
                System.out.println("Введите корректную дату в формате yyyy-mm-dd");
            } catch (TypeNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

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