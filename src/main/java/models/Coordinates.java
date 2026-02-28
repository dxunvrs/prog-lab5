package models;

/**
 * Модель для координат, данная по заданию
 *
 * @param x Значение поля должно быть больше -425, Поле не может быть null
 */
public record Coordinates(Long x, int y) {
}