package commands;

import core.CollectionRepository;
import exceptions.IdNotFoundException;
import models.Product;

import java.util.LinkedList;

public class RemoveCommand extends Command {
    private final CollectionRepository collectionManager;

    public RemoveCommand(CollectionRepository collectionManager) {
        super("remove_by_id", "remove_by_id - удалить элемент из коллекции по id", 1);
        this.collectionManager = collectionManager;
    }

    private int findIndex(int id) throws IdNotFoundException {
        LinkedList<Product> collection = collectionManager.getCollection();
        for (Product product: collection) {
            if (id == product.getId()) {
                return collection.indexOf(product);
            }
        }
        throw new IdNotFoundException("Такого id нет");
    }

    @Override
    protected void process() {
        int index;
        try {
            index = findIndex(Integer.parseInt(tokens[1]));
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
