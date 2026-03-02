package commands;

import core.CollectionManager;

/**
 * Команда для естественной сортировки
 */
public class SortCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public SortCommand() {
        super("sort", "sort - сортировка коллекции в естественном порядке (по id)", 0);
    }

    @Override
    public void execute(String[] tokens) {
        collectionManager.sort();
        System.out.println("Коллекция отсортирована в естественном порядке, введите show для просмотра");
    }
}
