package core;

import commands.Command;

import java.util.Iterator;
import java.util.Map;

/**
 * Интерфейс для взаимодействия с {@link CommandManager}
 */
public interface CommandRegistry {
    Map<String, Command> getCommandsMap();
    void addCommand(Command command);
    Iterator<String> getCommandsHistory();
    void addCommandToHistory(String commandName);
}
