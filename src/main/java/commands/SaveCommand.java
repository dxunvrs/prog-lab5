package commands;

import core.CollectionRepository;
import io.FileStorage;

public class SaveCommand extends Command {
    private final FileStorage fileManager;
    private final CollectionRepository collectionManager;

    public SaveCommand(CollectionRepository collectionManager, FileStorage fileManager) {
        super("save", "save - сохранение коллекции в файл", 0);
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    protected void process() {
        fileManager.save(collectionManager.getIterator(), collectionManager.getDateOfInit(), "test.csv");
    }
}
