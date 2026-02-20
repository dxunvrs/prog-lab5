package commands;

import commands.di.CollectionManagerDependant;
import core.CollectionRepository;

/**
 * Команда для естественной сортировки
 */
public class SortCommand extends Command implements CollectionManagerDependant {
    private CollectionRepository collectionManager;

    public SortCommand() {
        super("sort", "sort - сортировка коллекции в естественном порядке (по id)", 0);
    }

    @Override
    protected void process() {
        collectionManager.sort();
        System.out.println("Коллекция отсортирована в естественном порядке, введите show для просмотра");
    }

    @Override
    public void setCollectionManager(CollectionRepository collectionManager) {
        this.collectionManager = collectionManager;
    }
}
