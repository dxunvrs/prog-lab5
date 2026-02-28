package core;

import commands.Command;

import java.util.Map;
import java.util.stream.Stream;

/**
 * Интерфейс для взаимодействия с {@link CommandManager}
 */
public interface CommandRegistry {
    Map<String, Command> getCommandsMap();
    void addCommand(Command command);
    Stream<String> getCommandsHistory();
}
