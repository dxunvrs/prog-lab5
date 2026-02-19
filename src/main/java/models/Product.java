package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Date;

@JsonPropertyOrder({"id", "name", "coordinates", "creationDate", "price", "unitOfMeasure", "owner"})
public class Product implements Comparable<Product> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой

    @JsonUnwrapped(prefix = "coordinates_")
    private Coordinates coordinates; //Поле не может быть null

    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int price; //Значение поля должно быть больше 0
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null

    @JsonUnwrapped(prefix = "owner_")
    private Person owner; //Поле не может быть null

    public Product() {

    }

    public Product(Integer id, String name, Coordinates coordinates, Date creationDate, int price, UnitOfMeasure unitOfMeasure, Person owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
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

    public Date getCreationDate() {
        return creationDate;
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

    public void update(Product product) {
        id = product.getId();
        name = product.getName();
        coordinates = product.getCoordinates();
        creationDate = product.getCreationDate();
        price = product.getPrice();
        unitOfMeasure = product.getUnitOfMeasure();
        owner = product.getOwner();
    }

    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.id, other.getId());
    }
}
