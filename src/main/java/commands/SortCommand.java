package commands;

import core.CollectionManager;
import utility.ExecutionResponse;

/**
 * Команда для естественной сортировки
 */
public class SortCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public SortCommand() {
        super("sort", "sort - сортировка коллекции в естественном порядке (по id)", 0);
    }

    @Override
    public ExecutionResponse execute(String[] tokens) {
        collectionManager.sort();
        return new ExecutionResponse("Коллекция отсортирована в естественном порядке, введите show для просмотра", false);
    }
}
