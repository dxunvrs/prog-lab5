package commands;

import core.CollectionRepository;
import io.ExecuteContext;
import io.UserInput;
import utility.ProductForm;

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
        collectionRepository.addProduct(
                new ProductForm(userInput, executeContext.isScriptMode()).getProduct()
        );
    }
}