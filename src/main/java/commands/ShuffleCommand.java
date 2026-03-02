package commands;

import core.CollectionManager;

/**
 * Команда для рандомной сортировки
 */
public class ShuffleCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public ShuffleCommand() {
        super("shuffle", "shuffle - перемешать коллекцию в случайном порядке", 0);
    }

    @Override
    public void execute(String[] tokens) {
        collectionManager.randomSort();
        System.out.println("Коллекция перемешана, введите show для просмотра");
    }
}
