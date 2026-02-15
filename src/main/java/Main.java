import commands.AddCommand;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.InfoCommand;
import core.CollectionManager;
import core.CommandManager;
import core.CollectionRepository;
import core.CommandRegistry;
import io.ConsoleReader;
import models.Product;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        CommandRegistry commandManager = new CommandManager();
        CollectionRepository collectionManager = new CollectionManager(new LinkedList<Product>());
        ConsoleReader consoleReader = new ConsoleReader(commandManager);

        commandManager.addCommand(new HelpCommand(commandManager));
        commandManager.addCommand(new InfoCommand(collectionManager));
        commandManager.addCommand(new ExitCommand());
        commandManager.addCommand(new AddCommand(collectionManager));

        System.out.println("Ожидание ввода команды, для списка доступных команд - help");
        consoleReader.interactive();

    }
}
