package commands;

import core.CollectionManager;
import utility.ExecutionResponse;

/**
 * Команда для очищения коллекции
 */
public class ClearCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public ClearCommand() {
        super("clear", "clear - очистить коллекцию", 0);
    }

    @Override
    public ExecutionResponse execute(String[] tokens) {
        collectionManager.clearCollection();
        return new ExecutionResponse("Коллекция очищена", false);
    }
}
