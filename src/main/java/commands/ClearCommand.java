package commands;

import core.CollectionRepository;

public class ClearCommand extends Command {
    private final CollectionRepository collectionManager;

    public ClearCommand(CollectionRepository collectionManager) {
        super("clear", "clear - очистить коллекцию", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    protected void process() {
        collectionManager.clearCollection();
    }
}
