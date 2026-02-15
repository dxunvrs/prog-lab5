package commands;

import core.CollectionRepository;

public class InfoCommand extends Command {
    private final CollectionRepository collectionManager;

    public InfoCommand(CollectionRepository collectionManager) {
        super("info", "info - информация о коллекции", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    protected void process() {
        if (tokens.length == 1) {
            System.out.println("Количество элементов: " + collectionManager.getCollection().size());
        } else {
            System.out.println("Команду info стоит писать без аргументов");
        }
    }
}
