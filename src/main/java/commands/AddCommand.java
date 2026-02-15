package commands;

import core.CollectionRepository;
import models.Product;

import java.util.Date;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class AddCommand extends Command {
    private final CollectionRepository collectionManager;

    public AddCommand(CollectionRepository collectionManager) {
        super("add", "add - добавление нового элемента", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    protected void process() {
        LinkedList<Product> collection = collectionManager.getCollection();

        Integer id = collection.size() + 1;

        String name = askName();
        Long x = askX();
        int y = askY();
        Date creationDate = new Date();


        System.out.println(name + x);
    }

    private String askName() {
        String name = "";
        while (true) {
            try {
                name = reader.readNextLine("Введите имя: ");
                if (name.isEmpty()) {
                    System.out.println("Имя не может быть пустым");
                } else {
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Конец ввода. Завершение программы...");
                System.exit(0);
            }
        }
        return name;
    }

    private Long askX() {
        Long x = 0L;
        while (true) {
            try {
                x = Long.parseLong(reader.readNextLine("Введите координату X: "));
                if (x < -425) {
                    System.out.println("Координата по X должна быть больше -425");
                } else {
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Конец ввода. Завершение программы...");
                System.exit(0);
            } catch (NumberFormatException e) {
                System.out.println("Введите данные типа Long");
            }
        }
        return x;
    }

    private int askY() {
        int y = 0;
        while (true) {
            try {
                y = Integer.parseInt(reader.readNextLine("Введите координату Y: "));
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Конец ввода. Завершение программы...");
                System.exit(0);
            } catch (NumberFormatException e) {
                System.out.println("Введите данные типа int");
            }
        }
        return y;
    }
}
