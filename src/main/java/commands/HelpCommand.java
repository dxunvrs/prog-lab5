package commands;

import commands.di.CommandManagerDependant;
import core.CommandRegistry;

public class HelpCommand extends Command implements CommandManagerDependant {
    private CommandRegistry commandManager;

    public HelpCommand() {
        super("help", "help - список доступных команд", 0);
    }

    @Override
    protected void process() {
        System.out.println("Список команд и их описание: ");
        for (Command command : commandManager.getCommandsMap().values()) {
            System.out.println("  " + command.getDescription());
        }
    }

    @Override
    public void setCommandManager(CommandRegistry commandManager) {
        this.commandManager = commandManager;
    }
}
