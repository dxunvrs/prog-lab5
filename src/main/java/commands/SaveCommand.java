package commands;

import core.CollectionRepository;
import io.FileStorage;

/**
 * Команда для сохранения коллекции в файл, формат .csv
 */
public class SaveCommand extends Command {
    @Inject
    private CollectionRepository collectionRepository;

    @Inject
    private FileStorage fileStorage;


    public SaveCommand() {
        super("save", "save - сохранение коллекции в файл", 0);
    }

    @Override
    public void execute(String[] tokens) {
        fileStorage.save(collectionRepository.getIterator(), collectionRepository.getDateOfInit());
    }
}
