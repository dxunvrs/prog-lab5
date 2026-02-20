package commands;

import commands.di.CollectionManagerDependant;
import commands.di.ReaderDependant;
import core.CollectionRepository;
import io.UserInput;
import models.Product;
import utility.ProductForm;

import java.util.Iterator;

public class AddCommand extends Command implements CollectionManagerDependant, ReaderDependant {
    private CollectionRepository collectionManager;
    private UserInput reader;

    public AddCommand() {
        super("add", "add - добавление нового элемента", 0);
    }

    @Override
    protected void process() {
        ProductForm form = new ProductForm(reader, getMaxId()+1);
        collectionManager.addProduct(form.getProduct(null));
        System.out.println("Продукт добавлен");
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
    public void setCollectionManager(CollectionRepository collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void setReader(UserInput reader) {
        this.reader = reader;
    }
}