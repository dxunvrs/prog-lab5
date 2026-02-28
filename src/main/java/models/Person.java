package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

/**
 * Модель для человека, данная по заданию
 *
 * @param name     Поле не может быть null, Строка не может быть пустой
 * @param birthday Поле не может быть null
 * @param height   Поле не может быть null, Значение поля должно быть больше 0
 */
@JsonPropertyOrder({"name", "birthday", "height"})
public record Person(String name, LocalDate birthday, Long height) {
}
