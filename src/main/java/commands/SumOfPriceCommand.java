package commands;

import core.CollectionRepository;
import models.Product;

import java.util.Iterator;

/**
 * Команда для вывода суммы цен всех элементов коллекции
 */
public class SumOfPriceCommand extends Command {
    @Inject
    private CollectionRepository collectionRepository;

    public SumOfPriceCommand() {
        super("sum_of_price", "sum_of_price - вывести сумму цен всех элементов коллекции", 0);
    }

    @Override
    public void execute(String[] tokens) {
        System.out.println("Сумма цен всех элементов коллекции: " + collectionRepository.getSumOfPrice());
    }
}
