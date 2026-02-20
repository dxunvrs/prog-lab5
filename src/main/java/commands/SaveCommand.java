package commands;

import commands.di.CollectionManagerDependant;
import commands.di.FileManagerDependant;
import core.CollectionRepository;
import io.FileStorage;

/**
 * Команда для сохранения коллекции в файл, формат .csv
 */
public class SaveCommand extends Command implements CollectionManagerDependant, FileManagerDependant {
    private CollectionRepository collectionManager;
    private FileStorage fileManager;


    public SaveCommand() {
        super("save", "save - сохранение коллекции в файл", 0);
    }

    @Override
    protected void process() {
        fileManager.save(collectionManager.getIterator(), collectionManager.getDateOfInit());
    }

    @Override
    public void setCollectionManager(CollectionRepository collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void setFileManager(FileStorage fileManager) {
        this.fileManager = fileManager;
    }
}
