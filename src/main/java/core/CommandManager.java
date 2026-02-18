package core;

import commands.Command;

import java.util.*;

public class CommandManager implements CommandRegistry {
    private final Map<String, Command> commands;
    private final List<String> commandsHistory;

    public CommandManager() {
        commands = new HashMap<>();
        commandsHistory = new LinkedList<String>();
    }

    @Override
    public Iterator<String> getCommandsHistory() {
        return Collections.unmodifiableCollection(commandsHistory).iterator();
    }

    @Override
    public void addCommandToHistory(String commandName) {
        commandsHistory.add(commandName);
        if (commandsHistory.size() > 15) {
            commandsHistory.remove(0);
        }
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
