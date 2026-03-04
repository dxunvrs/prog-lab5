package commands;

import core.CollectionManager;
import utility.ExecutionResponse;

/**
 * Команда для рандомной сортировки
 */
public class ShuffleCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public ShuffleCommand() {
        super("shuffle", "shuffle - перемешать коллекцию в случайном порядке", 0);
    }

    @Override
    public ExecutionResponse execute(String[] tokens) {
        collectionManager.randomSort();
        return new ExecutionResponse("Коллекция перемешана, введите show для просмотра", false);
    }
}
