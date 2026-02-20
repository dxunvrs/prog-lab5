package models;

/**
 * Модель для координат, данная по заданию
 */
public class Coordinates {
    private Long x; //Значение поля должно быть больше -425, Поле не может быть null
    private int y;

    public Coordinates() {

    }

    public Coordinates(Long x, int y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void update(Coordinates coordinates) {
        x = coordinates.getX();
        y = coordinates.getY();
    }
}