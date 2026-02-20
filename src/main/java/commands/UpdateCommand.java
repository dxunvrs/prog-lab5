package commands;

import commands.di.CollectionManagerDependant;
import commands.di.ReaderDependant;
import core.CollectionRepository;
import exceptions.IdNotFoundException;
import io.UserInput;
import utility.ProductForm;

public class UpdateCommand extends Command implements CollectionManagerDependant, ReaderDependant {
    private CollectionRepository collectionManager;
    private UserInput reader;

    public UpdateCommand() {
        super("update", "update id - обновить значение элемента по заданному id", 1);
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

        ProductForm form = new ProductForm(reader, Integer.parseInt(tokens[1]), reader.isScriptMode());
        collectionManager.updateProduct(index, form.getProduct(collectionManager.getProduct(index).getCreationDate()));
        System.out.println("Продукт обновлен");
    }

    @Override
    public void setCollectionManager(CollectionRepository collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void setReader(UserInput reader) {
        this.reader = reader;
    }
}
