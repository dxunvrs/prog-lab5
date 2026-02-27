package commands;

import core.CollectionRepository;

/**
 * Команда для естественной сортировки
 */
public class SortCommand extends Command {
    @Inject
    private CollectionRepository collectionRepository;

    public SortCommand() {
        super("sort", "sort - сортировка коллекции в естественном порядке (по id)", 0);
    }

    @Override
    public void execute(String[] tokens) {
        collectionRepository.sort();
        System.out.println("Коллекция отсортирована в естественном порядке, введите show для просмотра");
    }
}
