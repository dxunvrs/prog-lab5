package commands;

import core.ICollectionManager;

public class InfoCommand extends Command {
    private final ICollectionManager collectionManager;

    public InfoCommand(ICollectionManager collectionManager) {
        super("info", "info - информация о коллекции", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    protected void process(String[] tokens) {
        if (tokens.length == 1) {
            System.out.println("Количество элементов: " + collectionManager.getCollection().size());
        } else {
            System.out.println("Команду info стоит писать без аргументов");
        }
    }
}
