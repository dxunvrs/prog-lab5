package commands;

import core.CollectionRepository;

/**
 * Команда для очищения коллекции
 */
public class ClearCommand extends Command {
    @Inject
    private CollectionRepository collectionRepository;

    public ClearCommand() {
        super("clear", "clear - очистить коллекцию", 0);
    }

    @Override
    public void execute(String[] tokens) {
        collectionRepository.clearCollection();
        System.out.println("Коллекция очищена");
    }
}
