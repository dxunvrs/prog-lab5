package commands;

import commands.di.CollectionManagerDependant;
import core.CollectionRepository;

/**
 * Команда для очищения коллекции
 */
public class ClearCommand extends Command implements CollectionManagerDependant {
    private CollectionRepository collectionManager;

    public ClearCommand() {
        super("clear", "clear - очистить коллекцию", 0);
    }

    @Override
    protected void process() {
        collectionManager.clearCollection();
        System.out.println("Коллекция очищена");
    }

    @Override
    public void setCollectionManager(CollectionRepository collectionManager) {
        this.collectionManager = collectionManager;
    }
}
