import commands.*;
import core.CollectionManager;
import core.CommandManager;
import core.CollectionRepository;
import core.CommandRegistry;
import io.ConsoleReader;
import io.FileManager;
import io.FileStorage;

public class Main {
    public static void main(String[] args) {
        CommandRegistry commandManager = new CommandManager();
        CollectionRepository collectionManager = new CollectionManager();
        ConsoleReader consoleReader = new ConsoleReader(commandManager);
        FileStorage fileManager = new FileManager();

        commandManager.addCommand(new HelpCommand(commandManager));
        commandManager.addCommand(new InfoCommand(collectionManager));
        commandManager.addCommand(new ExitCommand());
        commandManager.addCommand(new AddCommand(collectionManager, consoleReader));
        commandManager.addCommand(new ClearCommand(collectionManager));
        commandManager.addCommand(new ShowCommand(collectionManager));
        commandManager.addCommand(new UpdateCommand(collectionManager, consoleReader));
        commandManager.addCommand(new RemoveCommand(collectionManager));
        commandManager.addCommand(new HistoryCommand(commandManager));
        commandManager.addCommand(new SortCommand(collectionManager));
        commandManager.addCommand(new ShuffleCommand(collectionManager));
        commandManager.addCommand(new SumOfPriceCommand(collectionManager));
        commandManager.addCommand(new AverageOfPriceCommand(collectionManager));
        commandManager.addCommand(new FilterStartsWithNameCommand(collectionManager));
        commandManager.addCommand(new SaveCommand(collectionManager, fileManager));

        if (args.length == 0) {
            System.out.println("Имя файла с коллекцией не указано, создана новая коллекция");
        } else {
            if (args.length > 1) System.out.println("Указано больше одного аргумента, в качестве имени файла взят первый полученный аргумент");
            fileManager.setFileName(args[0]);
            fileManager.load(collectionManager);
        }

        System.out.println("Ожидание ввода команды, для списка доступных команд - help");
        consoleReader.interactive();
    }
}
