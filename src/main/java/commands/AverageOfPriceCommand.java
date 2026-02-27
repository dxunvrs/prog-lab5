package commands;

import core.CollectionRepository;
import models.Product;

import java.util.Iterator;

/**
 * Команда для поиска средней цены для всех элементов коллекции
 */
public class AverageOfPriceCommand extends Command {
    @Inject
    private CollectionRepository collectionRepository;

    public AverageOfPriceCommand() {
        super("average_of_price", "average_of_price - вывести среднее значение цены для всех элементов коллекции", 0);
    }

    @Override
    public void execute(String[] tokens) {
        System.out.printf("Среднее значение цены для всех элементов коллекции: %.2f" + "\n", avg());
    }

    private float avg() {
        int sum = 0;
        Iterator<Product> iterator = collectionRepository.getIterator();
        while (iterator.hasNext()) {
            sum += iterator.next().getPrice();
        }

        if (collectionRepository.getCollectionSize() == 0) {
            return 0f;
        }
        return (float) sum / collectionRepository.getCollectionSize();
    }
}
