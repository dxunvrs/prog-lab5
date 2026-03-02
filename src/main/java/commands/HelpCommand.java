package commands;

import core.CommandManager;

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
    public void execute(String[] tokens) {
        System.out.println("Список команд и их описание: ");
        for (Command command : commandManager.getCommandsMap().values()) {
            System.out.println("  " + command.getDescription());
        }
    }
}
