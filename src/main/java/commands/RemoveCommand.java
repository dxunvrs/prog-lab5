package commands;

import commands.di.CollectionManagerDependant;
import core.CollectionRepository;
import exceptions.IdNotFoundException;

/**
 * Команда для удаления элемента коллекции по id
 */
public class RemoveCommand extends Command implements CollectionManagerDependant {
    private CollectionRepository collectionManager;

    public RemoveCommand() {
        super("remove_by_id", "remove_by_id - удалить элемент из коллекции по id", 1);
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

        collectionManager.removeProduct(index);
        System.out.println("Продукт удален");
    }

    @Override
    public void setCollectionManager(CollectionRepository collectionManager) {
        this.collectionManager = collectionManager;
    }
}
