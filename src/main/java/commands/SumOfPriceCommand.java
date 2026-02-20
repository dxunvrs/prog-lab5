package commands;

import commands.di.CollectionManagerDependant;
import core.CollectionRepository;
import models.Product;

import java.util.Iterator;

public class SumOfPriceCommand extends Command implements CollectionManagerDependant {
    private CollectionRepository collectionManager;

    public SumOfPriceCommand() {
        super("sum_of_price", "sum_of_price - вывести сумму цен всех элементов коллекции", 0);
    }

    @Override
    protected void process() {
        System.out.println("Сумма цен всех элементов коллекции: " + sum());
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
    public void setCollectionManager(CollectionRepository collectionManager) {
        this.collectionManager = collectionManager;
    }
}
