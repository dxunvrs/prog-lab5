package commands;

import core.CollectionRepository;
import models.Product;

import java.util.Iterator;

public class AverageOfPriceCommand extends Command {
    private final CollectionRepository collectionManager;

    public AverageOfPriceCommand(CollectionRepository collectionManager) {
        super("average_of_price", "average_of_price - вывести среднее значение цены для всех элементов коллекции", 0);
        this.collectionManager = collectionManager;
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
    protected void process() {
        System.out.printf("Среднее значение цены для всех элементов коллекции: %.2f" + "\n", avg());
    }
}
