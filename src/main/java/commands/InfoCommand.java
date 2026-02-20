package commands;

import commands.di.CollectionManagerDependant;
import core.CollectionRepository;

import java.time.format.DateTimeFormatter;

/**
 * Команда для получения информации о коллекции (тип, дата создания, размер)
 */
public class InfoCommand extends Command implements CollectionManagerDependant {
    private CollectionRepository collectionManager;

    public InfoCommand() {
        super("info", "info - информация о коллекции", 0);
    }

    @Override
    protected void process() {
        System.out.println("Информация о коллекции: ");
        System.out.println("  Тип: LinkedList");
        System.out.println("  Дата инициализации: " + collectionManager.getDateOfInit().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("  Количество элементов: " + collectionManager.getCollectionSize());
    }

    @Override
    public void setCollectionManager(CollectionRepository collectionManager) {
        this.collectionManager = collectionManager;
    }
}
