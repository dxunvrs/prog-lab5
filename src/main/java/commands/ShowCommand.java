package commands;

import core.CollectionRepository;
import models.Product;

import java.util.Iterator;

/**
 * Команда для отображения всех элементов коллекции
 */
public class ShowCommand extends Command {
    @Inject
    private CollectionRepository collectionRepository;

    public ShowCommand() {
        super("show", "show - вывод элементов коллекции", 0);
    }

    @Override
    public void execute(String[] tokens) {
        System.out.println(collectionRepository.getFormattedCollection(product -> true));
    }
}
