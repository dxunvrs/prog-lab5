package commands;

import core.CollectionManager;
import utility.ExecutionResponse;

/**
 * Команда для поиска элементов, название которых начинается с заданной подстроки
 */
public class FilterStartsWithNameCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public FilterStartsWithNameCommand() {
        super("filter_starts_with_name", "filter_starts_with_name - вывести элементы, название которых начинается с заданной подстроки", 1);
    }

    @Override
    public ExecutionResponse execute(String[] tokens) {
        return new ExecutionResponse(
          collectionManager.getFormattedCollection(product -> product.getName().startsWith(tokens[1])),
          false
        );
    }
}
