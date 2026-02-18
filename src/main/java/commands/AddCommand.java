package commands;

import core.CollectionRepository;
import io.UserInput;
import models.Product;
import utility.ProductForm;

import java.util.Iterator;

public class AddCommand extends Command {
    private final CollectionRepository collectionManager;
    private final UserInput reader;

    public AddCommand(CollectionRepository collectionManager, UserInput reader) {
        super("add", "add - добавление нового элемента", 0);
        this.collectionManager = collectionManager;
        this.reader = reader;
    }

    private int getMaxId() {
        int id = 0;
        Iterator<Product> iterator = collectionManager.getIterator();
        while (iterator.hasNext()) {
            id = Math.max(id, iterator.next().getId());
        }
        return id;
    }

    @Override
    protected void process() {
        ProductForm form = new ProductForm(reader, getMaxId()+1);
        collectionManager.addElement(form.getProduct());
        System.out.println("Продукт добавлен");
    }
}