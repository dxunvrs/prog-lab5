package commands;

import core.CollectionRepository;
import models.Product;
import utility.ProductForm;

public class AddCommand extends Command {
    private final CollectionRepository collectionManager;

    public AddCommand(CollectionRepository collectionManager) {
        super("add", "add - добавление нового элемента", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    protected void process() {
        int id = 0;
        for (Product product: collectionManager.getCollection()) {
            id = Math.max(id, product.getId());
        }
        ProductForm form = new ProductForm(reader, id+1);
        collectionManager.addElement(form.getProduct());
        System.out.println("Продукт добавлен");
    }
}