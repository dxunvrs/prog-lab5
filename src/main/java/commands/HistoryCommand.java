package commands;

import core.CommandManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Команда для вывода последних 15 команд без аргументов
 */
public class HistoryCommand extends Command {
    @Inject
    private CommandManager commandManager;

    public HistoryCommand() {
        super("history", "history - вывести последние 15 команд без аргументов", 0);
    }

    @Override
    public void execute(String[] tokens) {
        System.out.println("Последние 15 команд: ");
        AtomicInteger index = new AtomicInteger(1);
        commandManager.getCommandsHistory().map(command -> index.getAndIncrement() + ". " + command).forEach(System.out::println);
    }
}
