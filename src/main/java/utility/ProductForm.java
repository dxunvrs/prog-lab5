package utility;

import io.UserInput;
import models.Coordinates;
import models.Person;
import models.Product;
import models.UnitOfMeasure;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Класс для запроса конкретной формы - формы продукта
 */
public class ProductForm extends Form {
    /**
     * Сгенерированное id для добавления, либо существующее id для обновления
     */
    private final Integer id;

    public ProductForm(UserInput reader, Integer id, boolean scriptMode) {
        super(reader, scriptMode);
        this.id = id;
    }

    /**
     * Метод получения продукта
     * @param creationDate при добавлении на вход null и берется дата в настоящий момент времени, если элемент обновляется, то дата создания не меняется
     */
    public Product getProduct(Date creationDate) {
        String name = ask(String.class, "название", value -> {
            if (value == null) {
                System.out.println("Имя не может быть пустым");
                return false;
            }
            return true;
        });

        Long x = ask(Long.class, "координату по X", value -> {
            if (value == null) {
                System.out.println("Координата по X не может быть пустой");
                return false;
            } else if (value < -425) {
                System.out.println("Координата по X должна быть больше -425");
                return false;
            }
            return true;
        });

        int y = ask(Integer.class, "координату по Y", value -> {
            if (value == null) {
                System.out.println("Координата по Y не может быть пустой");
                return false;
            }
            return true;
        });

        int price = ask(Integer.class, "цену", value -> {
            if (value == null) {
                System.out.println("Цена не должна быть пустой");
                return false;
            } else if (value <= 0) {
                System.out.println("Цена должна быть больше 0");
                return false;
            }
            return true;
        });

        UnitOfMeasure unitOfMeasure = ask(UnitOfMeasure.class, "единицу измерения(METERS, SQUARE_METERS, PCS, MILLILITERS)", value -> {
            if (value == null) {
                System.out.println("Единица измерения не может быть пустой");
                return false;
            }
            return true;
        });

        String ownerName = ask(String.class, "имя владельца", value -> {
            if (value == null) {
                System.out.println("Имя владельца не может быть пустым");
                return false;
            }
            return true;
        });

        LocalDate birthday = ask(LocalDate.class, "дату дня рождения владельца(в формате yyyy-mm-dd)", value -> {
            if (value == null) {
                System.out.println("Поле не может быть пустым");
                return false;
            }
            return true;
        });

        Long height = ask(Long.class, "рост владельца", value -> {
            if (value == null) {
                System.out.println("Рост владельца не может быть пустым");
                return false;
            } else if (value <= 0) {
                System.out.println("Рост владельца должен быть больше 0");
                return false;
            }
            return true;
        });

        return new Product(
                id, name, new Coordinates(x, y), Objects.requireNonNullElseGet(creationDate, Date::new), price,
                unitOfMeasure, new Person(ownerName, birthday, height)
        );
    }
}