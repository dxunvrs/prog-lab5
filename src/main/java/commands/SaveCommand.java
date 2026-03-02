package commands;

import core.CollectionManager;
import io.FileManager;

/**
 * Команда для сохранения коллекции в файл, формат .csv
 */
public class SaveCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    @Inject
    private FileManager fileManager;


    public SaveCommand() {
        super("save", "save - сохранение коллекции в файл", 0);
    }

    @Override
    public void execute(String[] tokens) {
        fileManager.save(collectionManager);
    }
}
