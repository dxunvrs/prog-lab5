package commands;

import core.CollectionManager;
import exceptions.IdNotFoundException;
import exceptions.InvalidIdException;
import io.InputReader;
import utility.ExecutionResponse;
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
    public ExecutionResponse execute(String[] tokens) {
        try {
            collectionManager.updateProductById(Integer.parseInt(tokens[1]), new ProductForm(inputReader).getProduct());
        } catch (NumberFormatException e) {
            throw new InvalidIdException("Неверный формат id");
        }

        return new ExecutionResponse("Продукт с id=" + tokens[1] + " обновлен", false);
    }
}
