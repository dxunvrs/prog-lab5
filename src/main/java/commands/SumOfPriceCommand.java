package commands;

import core.CollectionRepository;
import models.Product;

import java.util.Iterator;

public class SumOfPriceCommand extends Command {
    private final CollectionRepository collectionManager;

    public SumOfPriceCommand(CollectionRepository collectionManager) {
        super("sum_of_price", "sum_of_price - вывести сумму цен всех элементов коллекции", 0);
        this.collectionManager = collectionManager;
    }

    private int sum() {
        int sum = 0;
        Iterator<Product> iterator = collectionManager.getIterator();
        while (iterator.hasNext()) {
            sum += iterator.next().getPrice();
        }
        return sum;
    }

    @Override
    protected void process() {
        System.out.println("Сумма цен всех элементов коллекции: " + sum());
    }
}
