package commands;

import core.CollectionManager;
import utility.ExecutionResponse;

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
    public ExecutionResponse execute(String[] tokens) {
        String responseMessage = String.format("Среднее значение цены для всех элементов коллекции: %.2f" + "\n", collectionManager.getAvgOfPrice());
        return new ExecutionResponse(responseMessage, false);
    }
}
