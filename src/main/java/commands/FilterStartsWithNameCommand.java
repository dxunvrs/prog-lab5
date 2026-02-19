package commands;

import core.CollectionRepository;
import models.Product;

import java.util.Iterator;

public class FilterStartsWithNameCommand extends Command {
    private final CollectionRepository collectionManager;

    public FilterStartsWithNameCommand(CollectionRepository collectionManager) {
        super("filter_starts_with_name", "filter_starts_with_name - вывести элементы, имя которых начинается с заданной подстроки", 1);
        this.collectionManager = collectionManager;
    }

    @Override
    protected void process() {
        Iterator<Product> iterator = collectionManager.getIterator();
        boolean hasMatch = false;
        System.out.println("Продукты, имя которых начинается на " + tokens[1]);
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (!(product.getName().startsWith(tokens[1]))) {
                continue;
            }
            hasMatch = true;
            System.out.println("Продукт №" + product.getId());
            System.out.println("  Название: " + product.getName());
            System.out.println("  Координаты: (" + product.getCoordinates().getX() + ", " + product.getCoordinates().getY() + ")");
            System.out.println("  Дата создания: " + product.getCreationDate());
            System.out.println("  Цена: " + product.getPrice());
            System.out.println("  Единицы измерения: " + product.getUnitOfMeasure().name());
            System.out.println("  Имя владельца: " + product.getOwner().getName());
            System.out.println("  Дата дня рождения владельца: " + product.getOwner().getBirthday());
            System.out.println("  Рост владельца: " + product.getOwner().getHeight());
        }
        if (!hasMatch) {
            System.out.println("Совпадений не найдено");
        }
    }
}
