import commands.*;
import core.CollectionManager;
import core.CommandManager;
import core.CollectionRepository;
import core.CommandRegistry;
import io.ConsoleReader;
import io.FileManager;
import io.FileStorage;
import io.UserInput;

public class Main {
    public static void main(String[] args) {
        CollectionRepository collectionManager = new CollectionManager();
        UserInput consoleReader = new ConsoleReader();
        FileStorage fileManager = new FileManager();
        CommandRegistry commandManager = new CommandManager(collectionManager, consoleReader, fileManager);

        commandManager.addCommand(new HelpCommand());
        commandManager.addCommand(new InfoCommand());
        commandManager.addCommand(new ExitCommand());
        commandManager.addCommand(new AddCommand());
        commandManager.addCommand(new ClearCommand());
        commandManager.addCommand(new ShowCommand());
        commandManager.addCommand(new UpdateCommand());
        commandManager.addCommand(new RemoveCommand());
        commandManager.addCommand(new HistoryCommand());
        commandManager.addCommand(new SortCommand());
        commandManager.addCommand(new ShuffleCommand());
        commandManager.addCommand(new SumOfPriceCommand());
        commandManager.addCommand(new AverageOfPriceCommand());
        commandManager.addCommand(new FilterStartsWithNameCommand());
        commandManager.addCommand(new SaveCommand());
        commandManager.addCommand(new ExecuteScriptCommand());

        if (args.length == 0) {
            System.out.println("Имя файла с коллекцией не указано, создана новая коллекция");
        } else {
            if (args.length > 1) System.out.println("Указано больше одного аргумента, в качестве имени файла взят первый полученный аргумент");
            fileManager.setFileName(args[0]);
            fileManager.load(collectionManager);
        }

        System.out.println("Ожидание ввода команды, для списка доступных команд - help");
        consoleReader.interactive(commandManager);
    }
}
