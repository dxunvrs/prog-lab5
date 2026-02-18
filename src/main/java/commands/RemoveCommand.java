package commands;

import core.CollectionRepository;
import exceptions.IdNotFoundException;

public class RemoveCommand extends Command {
    private final CollectionRepository collectionManager;

    public RemoveCommand(CollectionRepository collectionManager) {
        super("remove_by_id", "remove_by_id - удалить элемент из коллекции по id", 1);
        this.collectionManager = collectionManager;
    }

    @Override
    protected void process() {
        int index;
        try {
            index = collectionManager.findIndexById(Integer.parseInt(tokens[1]));
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат id");
            return;
        } catch (IdNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        collectionManager.removeById(index);
        System.out.println("Продукт удален");
    }
}
