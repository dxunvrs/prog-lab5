package commands;

import core.CollectionRepository;
import io.ExecuteContext;
import io.UserInput;
import utility.ProductForm;

/**
 * Команда для обновления элемента коллекции по заданному id
 */
public class UpdateCommand extends Command {
    @Inject
    private CollectionRepository collectionRepository;

    @Inject
    private UserInput userInput;

    @Inject
    private ExecuteContext executeContext;

    public UpdateCommand() {
        super("update", "update id - обновить значение элемента по заданному id", 1);
    }

    @Override
    public void execute(String[] tokens) {
        collectionRepository.updateProductById(Integer.parseInt(tokens[1]),
                new ProductForm(userInput, executeContext.isScriptMode()));
        System.out.println("Продукт обновлен");
    }
}
