package commands;

import core.CollectionRepository;

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
        System.out.printf("Среднее значение цены для всех элементов коллекции: %.2f" + "\n", collectionRepository.getAvgOfPrice());
    }
}
