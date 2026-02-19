package commands;

import core.CommandRegistry;

public class HelpCommand extends Command {
    private final CommandRegistry commandManager;

    public HelpCommand(CommandRegistry commandManager) {
        super("help", "help - список доступных команд", 0);
        this.commandManager = commandManager;
    }

    @Override
    protected void process() {
        System.out.println("Список команд и их описание: ");
        for (Command command : commandManager.getCommandsMap().values()) {
            System.out.println("  " + command.getDescription());
        }
    }
}
