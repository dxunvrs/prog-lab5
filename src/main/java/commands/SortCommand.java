package commands;

import core.CollectionRepository;

public class SortCommand extends Command {
    private final CollectionRepository collectionManager;

    public SortCommand(CollectionRepository collectionManager) {
        super("sort", "sort - сортировка коллекции в естественном порядке (по id)", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    protected void process() {
        collectionManager.sort();
        System.out.println("Коллекция отсортирована в естественном порядке, введите show для просмотра");
    }
}
