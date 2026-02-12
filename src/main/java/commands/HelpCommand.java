package commands;

import core.ICommandManager;

public class HelpCommand extends Command {
    private final ICommandManager commandManager;

    public HelpCommand(ICommandManager commandManager) {
        super("help", "help - список доступных команд", 0);
        this.commandManager = commandManager;
    }

    @Override
    protected void process(String[] tokens) {
        for (Command command : commandManager.getCommandsMap().values()) {
            System.out.println(command.getDescription());
        }
    }
}
