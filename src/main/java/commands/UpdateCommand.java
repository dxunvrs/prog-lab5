package commands;

import core.CollectionRepository;
import exceptions.IdNotFoundException;
import io.UserInput;
import utility.ProductForm;

public class UpdateCommand extends Command {
    private final CollectionRepository collectionManager;
    private final UserInput reader;

    public UpdateCommand(CollectionRepository collectionManager, UserInput reader) {
        super("update", "update id - обновить значение элемента по заданному id", 1);
        this.collectionManager = collectionManager;
        this.reader = reader;
    }

    @Override
    protected void process() {
        int index;
        try {
            index = collectionManager.findIndexById(Integer.parseInt(tokens[1]));
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат id");
            return;
        } catch (IdNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        ProductForm form = new ProductForm(reader, Integer.parseInt(tokens[1]));
        collectionManager.updateProduct(index, form.getProduct());
        System.out.println("Продукт обновлен");
    }
}
