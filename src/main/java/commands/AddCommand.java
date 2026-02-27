package commands;

import core.CollectionRepository;
import io.ExecuteContext;
import io.UserInput;
import models.Product;
import utility.ProductForm;

import java.util.Iterator;

/**
 * Команда для добавления нового элемента
 */
public class AddCommand extends Command {
    @Inject
    private CollectionRepository collectionRepository;

    @Inject
    private UserInput userInput;

    @Inject
    private ExecuteContext executeContext;

    public AddCommand() {
        super("add", "add - добавление нового элемента", 0);
    }

    @Override
    public void execute(String[] tokens) {
        ProductForm form = new ProductForm(userInput, getMaxId()+1, executeContext.isScriptMode());
        collectionRepository.addProduct(form.getProduct(null));
        System.out.println("Продукт добавлен");
    }

    /**
     * Метод для поиска максимального id среди всех продуктов.
     * Гарантирует уникальность id
     */
    private int getMaxId() {
        int id = 0;
        Iterator<Product> iterator = collectionRepository.getIterator();
        while (iterator.hasNext()) {
            id = Math.max(id, iterator.next().getId());
        }
        return id;
    }
}