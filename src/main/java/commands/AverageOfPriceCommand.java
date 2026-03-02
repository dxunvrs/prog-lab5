package commands;

import core.CollectionManager;

/**
 * Команда для поиска средней цены для всех элементов коллекции
 */
public class AverageOfPriceCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public AverageOfPriceCommand() {
        super("average_of_price", "average_of_price - вывести среднее значение цены для всех элементов коллекции", 0);
    }

    @Override
    public void execute(String[] tokens) {
        System.out.printf("Среднее значение цены для всех элементов коллекции: %.2f" + "\n", collectionManager.getAvgOfPrice());
    }
}
