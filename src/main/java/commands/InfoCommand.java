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
            System.out.println("Тип: LinkedList");
            System.out.println("Дата инициализации: " + collectionManager.getDateOfInit());
            System.out.println("Количество элементов: " + collectionManager.getCollectionSize());
        } else {
            System.out.println("Команду info стоит писать без аргументов");
        }
    }
}
