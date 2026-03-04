package commands;

import core.CollectionManager;
import utility.ExecutionResponse;

/**
 * Команда для отображения всех элементов коллекции
 */
public class ShowCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public ShowCommand() {
        super("show", "show - вывод элементов коллекции", 0);
    }

    @Override
    public ExecutionResponse execute(String[] tokens) {
        return new ExecutionResponse(collectionManager.getFormattedCollection(product -> true), false);
    }
}
