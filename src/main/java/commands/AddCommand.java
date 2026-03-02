package commands;

import core.CollectionManager;
import io.InputReader;
import utility.ProductForm;

/**
 * Команда для добавления нового элемента
 */
public class AddCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    @Inject
    private InputReader inputReader;

    public AddCommand() {
        super("add", "add - добавление нового элемента", 0);
    }

    @Override
    public void execute(String[] tokens) {
        collectionManager.addProduct(
                new ProductForm(inputReader, inputReader.isScriptMode()).getProduct()
        );
        System.out.println("Продукт добавлен");
    }
}