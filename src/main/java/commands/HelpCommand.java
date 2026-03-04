package commands;

import core.CommandManager;
import utility.ExecutionResponse;

/**
 * Команда для вывода списка доступных команд
 */
public class HelpCommand extends Command {
    @Inject
    private CommandManager commandManager;

    public HelpCommand() {
        super("help", "help - список доступных команд", 0);
    }

    @Override
    public ExecutionResponse execute(String[] tokens) {
        return new ExecutionResponse(commandManager.getFormattedCommandsList(), false);
    }
}
