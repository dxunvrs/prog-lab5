package core;

import commands.Command;

import java.util.Map;

public interface ICommandManager {
    Map<String, Command> getCommandsMap();
    void addCommand(Command newCommand);
}
