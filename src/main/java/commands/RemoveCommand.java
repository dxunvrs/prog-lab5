package commands;

import core.CollectionManager;
import exceptions.IdNotFoundException;
import exceptions.InvalidIdException;
import utility.ExecutionResponse;

/**
 * Команда для удаления элемента коллекции по id
 */
public class RemoveCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public RemoveCommand() {
        super("remove_by_id", "remove_by_id - удалить элемент из коллекции по id", 1);
    }

    @Override
    public ExecutionResponse execute(String[] tokens) {
        try {
            collectionManager.removeProductById(Integer.parseInt(tokens[1]));
        } catch (NumberFormatException e) {
            throw new InvalidIdException("Неверный формат id");
        }

        return new ExecutionResponse("Продукт с id=" + tokens[1] + " удален", false);
    }
}
