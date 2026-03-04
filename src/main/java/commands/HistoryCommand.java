package commands;

import core.CommandManager;
import utility.ExecutionResponse;

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
    public ExecutionResponse execute(String[] tokens) {
        return new ExecutionResponse(commandManager.getFormattedHistory(), false);
    }
}
