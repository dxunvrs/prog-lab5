package commands;

import core.CollectionManager;

/**
 * Команда для отображения всех элементов коллекции
 */
public class ShowCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public ShowCommand() {
        super("show", "show - вывод элементов коллекции", 0);
    }

    @Override
    public void execute(String[] tokens) {
        System.out.println(collectionManager.getFormattedCollection(product -> true));
    }
}
