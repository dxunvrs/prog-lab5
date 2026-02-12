package core;

import commands.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements ICommandManager {
    private final Map<String, Command> commands;

    public CommandManager(Map<String, Command> commands) {
        this.commands = commands;
    }

    public CommandManager() {
        commands = new HashMap<>();
    }

    @Override
    public Map<String, Command> getCommandsMap() {
        return commands;
    }

    @Override
    public void addCommand(Command newCommand) {
        commands.put(newCommand.getName(), newCommand);
    }
}
