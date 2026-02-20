package commands;

import commands.di.CollectionManagerDependant;
import core.CollectionRepository;

public class ShuffleCommand extends Command implements CollectionManagerDependant {
    private CollectionRepository collectionManager;

    public ShuffleCommand() {
        super("shuffle", "shuffle - перемешать коллекцию в случайном порядке", 0);
    }

    @Override
    protected void process() {
        collectionManager.randomSort();
        System.out.println("Коллекция перемешана, введите show для просмотра");
    }

    @Override
    public void setCollectionManager(CollectionRepository collectionManager) {
        this.collectionManager = collectionManager;
    }
}
