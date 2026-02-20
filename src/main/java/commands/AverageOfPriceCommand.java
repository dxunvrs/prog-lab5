package commands;

import commands.di.CollectionManagerDependant;
import core.CollectionRepository;
import models.Product;

import java.util.Iterator;

/**
 * Команда для поиска средней цены для всех элементов коллекции
 */
public class AverageOfPriceCommand extends Command implements CollectionManagerDependant {
    private CollectionRepository collectionManager;

    public AverageOfPriceCommand() {
        super("average_of_price", "average_of_price - вывести среднее значение цены для всех элементов коллекции", 0);
    }

    @Override
    protected void process() {
        System.out.printf("Среднее значение цены для всех элементов коллекции: %.2f" + "\n", avg());
    }

    private float avg() {
        int sum = 0;
        Iterator<Product> iterator = collectionManager.getIterator();
        while (iterator.hasNext()) {
            sum += iterator.next().getPrice();
        }

        if (collectionManager.getCollectionSize() == 0) {
            return 0f;
        }
        return (float) sum / collectionManager.getCollectionSize();
    }

    @Override
    public void setCollectionManager(CollectionRepository collectionManager) {
        this.collectionManager = collectionManager;
    }
}
