package core;

import commands.*;
import exceptions.EndOfInputException;
import io.FileManager;
import io.InputReader;

public class ConsoleApp {
    private final CollectionManager collectionManager = new CollectionManager();
    private final InputReader inputReader = new InputReader();
    private final FileManager fileManager = new FileManager();
    private final CommandManager commandManager = new CommandManager(collectionManager, inputReader, fileManager);

    private boolean isWorking = true;

    public ConsoleApp(String[] args) {
        checkArgs(args);
        registerAllCommands();
    }

    public void interactive() {
        System.out.println("Ожидание ввода команды, для списка доступных команд - help");
        while (isWorking) {
            try {
                String line = inputReader.readNextLine("> ");
                String formattedLine = line.trim().replaceAll("\\s+", " ");

                if (inputReader.isScriptMode()) System.out.println(formattedLine); // для режима скрипта

                if (formattedLine.isEmpty()) continue;

                if (!commandManager.executeCommand(formattedLine)) {
                    isWorking = false;
                }
            } catch (EndOfInputException e) {
                System.out.println(e.getMessage());
                isWorking = false;
            }
        }
    }

    private void checkArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("Имя файла с коллекцией не указано, создана новая коллекция");
        } else {
            if (args.length > 1) System.out.println("Указано больше одного аргумента, в качестве имени файла взят первый полученный аргумент");
            fileManager.setFileName(args[0]);
            fileManager.load(collectionManager);
        }
    }

    private void registerAllCommands() {
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
    }
}
