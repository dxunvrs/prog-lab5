package core;

import commands.*;
import commands.di.CollectionManagerDependant;
import commands.di.CommandManagerDependant;
import commands.di.FileManagerDependant;
import commands.di.ReaderDependant;
import io.FileStorage;
import io.UserInput;

import java.util.*;

public class CommandManager implements CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();
    private final List<String> commandsHistory = new LinkedList<>();
    private final CollectionRepository collectionManager;
    private final UserInput reader;
    private final FileStorage fileManager;

    public CommandManager(CollectionRepository collectionManager, UserInput reader, FileStorage fileManager) {
        this.collectionManager = collectionManager;
        this.reader = reader;
        this.fileManager = fileManager;
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
    public void addCommand(Command command) {
        if (command instanceof CollectionManagerDependant) {
            ((CollectionManagerDependant) command).setCollectionManager(collectionManager);
        }
        if (command instanceof CommandManagerDependant) {
            ((CommandManagerDependant) command).setCommandManager(this);
        }
        if (command instanceof ReaderDependant) {
            ((ReaderDependant) command).setReader(reader);
        }
        if (command instanceof FileManagerDependant) {
            ((FileManagerDependant) command).setFileManager(fileManager);
        }

        commands.put(command.getName(), command);
    }
}
