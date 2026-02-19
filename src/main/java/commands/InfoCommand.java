package commands;

import core.CollectionRepository;

import java.time.format.DateTimeFormatter;

public class InfoCommand extends Command {
    private final CollectionRepository collectionManager;

    public InfoCommand(CollectionRepository collectionManager) {
        super("info", "info - информация о коллекции", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    protected void process() {
        System.out.println("Информация о коллекции: ");
        System.out.println("  Тип: LinkedList");
        System.out.println("  Дата инициализации: " + collectionManager.getDateOfInit().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("  Количество элементов: " + collectionManager.getCollectionSize());
    }
}
