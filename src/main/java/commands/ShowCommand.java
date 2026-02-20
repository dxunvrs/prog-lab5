package commands;

import commands.di.CollectionManagerDependant;
import core.CollectionRepository;
import models.Product;

import java.util.Iterator;

/**
 * Команда для отображения всех элементов коллекции
 */
public class ShowCommand extends Command implements CollectionManagerDependant {
    private CollectionRepository collectionManger;

    public ShowCommand() {
        super("show", "show - вывод элементов коллекции", 0);
    }

    @Override
    protected void process() {
        Iterator<Product> iterator = collectionManger.getIterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
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
    }

    @Override
    public void setCollectionManager(CollectionRepository collectionManger) {
        this.collectionManger = collectionManger;
    }
}
