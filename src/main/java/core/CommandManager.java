package core;

import commands.*;
import commands.di.CollectionManagerDependant;
import commands.di.CommandManagerDependant;
import commands.di.FileManagerDependant;
import commands.di.ReaderDependant;
import io.FileStorage;
import io.UserInput;

import java.util.*;

/**
 * Менеджер для управления командами.
 * Задачи: регистрация команд, dependency injection в команды, хранение хэш-мапы доступных команд
 */
public class CommandManager implements CommandRegistry {
    /**
     * Хэш-мап для доступных команд. Быстрый поиск за O(1), невозможность повтора
     */
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * LinkedList для истории команд, быстрое добавление в конец и удаление из начала
     */
    private final List<String> commandsHistory = new LinkedList<>();

    private final CollectionRepository collectionManager;
    private final UserInput reader;
    private final FileStorage fileManager;

    public CommandManager(CollectionRepository collectionManager, UserInput reader, FileStorage fileManager) {
        this.collectionManager = collectionManager;
        this.reader = reader;
        this.fileManager = fileManager;
    }

    /**
     * Получение истории последних 15 команд
     * @return Итератор коллекции истории команд
     */
    @Override
    public Iterator<String> getCommandsHistory() {
        return Collections.unmodifiableCollection(commandsHistory).iterator();
    }

    /**
     * Добавление выполненной команды в историю
     * Если размер превышает 15, то первый элемент удаляется
     * @param commandName имя команды
     */
    @Override
    public void addCommandToHistory(String commandName) {
        commandsHistory.add(commandName);
        if (commandsHistory.size() > 15) {
            commandsHistory.remove(0);
        }
    }

    /**
     * Получение хэш-мапы доступных команд
     */
    @Override
    public Map<String, Command> getCommandsMap() {
        return commands;
    }

    /**
     * Регистрация команды и прокидывание необходимых зависимостей
     */
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
