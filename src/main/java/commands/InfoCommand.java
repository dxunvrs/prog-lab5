package commands;

import core.CollectionRepository;

import java.time.format.DateTimeFormatter;

/**
 * Команда для получения информации о коллекции (тип, дата создания, размер)
 */
public class InfoCommand extends Command {
    @Inject
    private CollectionRepository collectionRepository;

    public InfoCommand() {
        super("info", "info - информация о коллекции", 0);
    }

    @Override
    public void execute(String[] tokens) {
        System.out.println("Информация о коллекции: ");
        System.out.println("  Тип: LinkedList");
        System.out.println("  Дата инициализации: " + collectionRepository.getDateOfInit().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("  Количество элементов: " + collectionRepository.getCollectionSize());
    }
}
