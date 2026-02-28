package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Date;

/**
 * Модель для продукта, данная по заданию
 */
@JsonPropertyOrder({"id", "name", "coordinates", "creationDate", "price", "unitOfMeasure", "owner"})
public class Product implements Comparable<Product> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой

    @JsonUnwrapped(prefix = "coordinates_")
    private Coordinates coordinates; //Поле не может быть null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private int price; //Значение поля должно быть больше 0
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null

    @JsonUnwrapped(prefix = "owner_")
    private Person owner; //Поле не может быть null

    public Product() {}

    public Product(String name, Coordinates coordinates, int price, UnitOfMeasure unitOfMeasure, Person owner) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getPrice() {
        return price;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Person getOwner() {
        return owner;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void update(Product product) {
        this.name = product.getName();
        this.coordinates = product.getCoordinates();
        this.price = product.getPrice();
        this.unitOfMeasure = product.getUnitOfMeasure();
        this.owner = product.getOwner();
    }

    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.id, other.getId());
    }

    public String toFormattedString() {
        return """
               Продукт №%d
                 Название: %s
                 Координаты: (%d, %d)
                 Дата создания: %s
                 Цена: %d
                 Единица измерения: %s
                 Имя владельца: %s
                 День рождения владельца: %s
                 Рост владельца: %d""".formatted(id, name, coordinates.x(), coordinates.y(),
                creationDate, price, unitOfMeasure.name(), owner.name(), owner.birthday(), owner.height());
    }
}
