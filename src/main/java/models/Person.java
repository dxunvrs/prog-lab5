package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

/**
 * Модель для человека, данная по заданию
 */
@JsonPropertyOrder({"name", "birthday", "height"})
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDate birthday; //Поле не может быть null
    private Long height; //Поле не может быть null, Значение поля должно быть больше 0

    public Person() {

    }

    public Person(String name, LocalDate birthday, Long height) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Long getHeight() {
        return height;
    }
}
