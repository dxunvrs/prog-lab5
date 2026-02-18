package commands;

import core.CollectionRepository;

public class ShuffleCommand extends Command {
    private final CollectionRepository collectionManager;

    public ShuffleCommand(CollectionRepository collectionManager) {
        super("shuffle", "shuffle - перемешать коллекцию в случайном порядке", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    protected void process() {
        collectionManager.randomSort();
    }
}
