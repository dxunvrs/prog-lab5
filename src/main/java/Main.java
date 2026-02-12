import commands.HelpCommand;
import commands.InfoCommand;
import core.CollectionManager;
import core.CommandManager;
import core.ICollectionManager;
import core.ICommandManager;
import io.ConsoleReader;
import models.Product;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        ICommandManager commandManager = new CommandManager();
        ICollectionManager collectionManager = new CollectionManager(new LinkedList<Product>());
        ConsoleReader consoleReader = new ConsoleReader(commandManager);

        commandManager.addCommand(new HelpCommand(commandManager));
        commandManager.addCommand(new InfoCommand(collectionManager));

        consoleReader.interactive();

    }
}
