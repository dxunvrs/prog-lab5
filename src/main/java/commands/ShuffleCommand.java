package commands;

import core.CollectionRepository;

/**
 * Команда для рандомной сортировки
 */
public class ShuffleCommand extends Command {
    @Inject
    private CollectionRepository collectionRepository;

    public ShuffleCommand() {
        super("shuffle", "shuffle - перемешать коллекцию в случайном порядке", 0);
    }

    @Override
    public void execute(String[] tokens) {
        collectionRepository.randomSort();
        System.out.println("Коллекция перемешана, введите show для просмотра");
    }
}
