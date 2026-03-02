package commands;

import core.CollectionManager;

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
    public void execute(String[] tokens) {
        collectionManager.removeProductById(Integer.parseInt(tokens[1]));
        System.out.println("Продукт удален");
    }
}
