package commands;

import core.CollectionRepository;
import exceptions.IdNotFoundException;

/**
 * Команда для удаления элемента коллекции по id
 */
public class RemoveCommand extends Command {
    @Inject
    private CollectionRepository collectionRepository;

    public RemoveCommand() {
        super("remove_by_id", "remove_by_id - удалить элемент из коллекции по id", 1);
    }

    @Override
    public void execute(String[] tokens) {
        collectionRepository.removeProductById(Integer.parseInt(tokens[1]));
    }
}
