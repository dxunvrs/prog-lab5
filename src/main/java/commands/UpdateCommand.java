package commands;

import core.CollectionRepository;
import exceptions.IdNotFoundException;
import models.Product;
import utility.ProductForm;

import java.util.LinkedList;

public class UpdateCommand extends Command {
    private final CollectionRepository collectionManager;

    public UpdateCommand(CollectionRepository collectionManager) {
        super("update", "update id - обновить значение элемента по заданному id", 1);
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

        ProductForm form = new ProductForm(reader, Integer.parseInt(tokens[1]));
        collectionManager.updateProduct(index, form.getProduct());
        System.out.println("Продукт обновлен");
    }
}
