package commands;

import core.CollectionManager;

/**
 * Команда для очищения коллекции
 */
public class ClearCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public ClearCommand() {
        super("clear", "clear - очистить коллекцию", 0);
    }

    @Override
    public void execute(String[] tokens) {
        collectionManager.clearCollection();
        System.out.println("Коллекция очищена");
    }
}
