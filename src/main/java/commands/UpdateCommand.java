package commands;

import core.CollectionRepository;
import exceptions.IdNotFoundException;
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
        int index;
        try {
            index = collectionRepository.findIndexById(Integer.parseInt(tokens[1]));
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат id");
            return;
        } catch (IdNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        ProductForm form = new ProductForm(userInput, Integer.parseInt(tokens[1]), executeContext.isScriptMode());
        collectionRepository.updateProduct(index, form.getProduct(collectionRepository.getProduct(index).getCreationDate()));
        System.out.println("Продукт обновлен");
    }
}
