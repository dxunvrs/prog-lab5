package commands;

import core.CollectionManager;

/**
 * Команда для вывода суммы цен всех элементов коллекции
 */
public class SumOfPriceCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public SumOfPriceCommand() {
        super("sum_of_price", "sum_of_price - вывести сумму цен всех элементов коллекции", 0);
    }

    @Override
    public void execute(String[] tokens) {
        System.out.println("Сумма цен всех элементов коллекции: " + collectionManager.getSumOfPrice());
    }
}
