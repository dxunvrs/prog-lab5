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
        int index;
        try {
            index = collectionRepository.findIndexById(Integer.parseInt(tokens[1]));
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат id");
            return;
        } catch (IdNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        collectionRepository.removeProduct(index);
        System.out.println("Продукт удален");
    }
}
