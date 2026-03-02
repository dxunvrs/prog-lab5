package commands;

import core.CollectionManager;
import io.InputReader;
import utility.ProductForm;

/**
 * Команда для обновления элемента коллекции по заданному id
 */
public class UpdateCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    @Inject
    private InputReader inputReader;

    public UpdateCommand() {
        super("update", "update id - обновить значение элемента по заданному id", 1);
    }

    @Override
    public void execute(String[] tokens) {
        collectionManager.updateProductById(Integer.parseInt(tokens[1]),
                new ProductForm(inputReader, inputReader.isScriptMode()));
        System.out.println("Продукт обновлен");
    }
}
