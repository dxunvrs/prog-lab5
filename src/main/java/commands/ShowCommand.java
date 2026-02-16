package commands;

import core.CollectionRepository;
import models.Product;

public class ShowCommand extends Command {
    private final CollectionRepository collectionManger;

    public ShowCommand(CollectionRepository collectionManger) {
        super("show", "show - вывод элементов коллекции", 0);
        this.collectionManger = collectionManger;
    }

    @Override
    protected void process() {
        for (Product product: collectionManger.getCollection()) {
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
}
