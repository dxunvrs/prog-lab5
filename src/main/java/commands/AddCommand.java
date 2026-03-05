package commands;

import core.CollectionManager;
import io.InputReader;
import utility.ExecutionResponse;
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
    public ExecutionResponse execute(String[] tokens) {
        collectionManager.addProduct(
                new ProductForm(inputReader).getProduct()
        );
        return new ExecutionResponse("Продукт добавлен", false);
    }
}