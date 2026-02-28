package core;

import commands.Command;
import commands.Inject;
import exceptions.EndOfExecutionException;
import exceptions.IdNotFoundException;
import io.FileStorage;
import io.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;

/**
 * Менеджер для управления командами.
 * Задачи: регистрация команд, dependency injection в команды, хранение хэш-мапы доступных команд
 */
public class CommandManager implements CommandRegistry, CommandExecutor {
    private static final Logger logger = LoggerFactory.getLogger(CommandManager.class);
    /**
     * Хэш-мап для доступных команд. Быстрый поиск за O(1), невозможность повтора
     */
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * LinkedList для истории команд, быстрое добавление в конец и удаление из начала
     */
    private final List<String> commandsHistory = new LinkedList<>();

    private final CollectionRepository collectionManager;
    private final Reader reader;
    private final FileStorage fileManager;

    public CommandManager(CollectionRepository collectionManager, Reader reader, FileStorage fileManager) {
        this.collectionManager = collectionManager;
        this.reader = reader;
        this.fileManager = fileManager;
        reader.setCommandExecutor(this);
    }

    @Override
    public boolean execute(String line, boolean isScriptMode) {
        String[] tokens = line.split(" ");
        Command command = commands.get(tokens[0]);
        if (command == null) {
            System.out.println("Команда " + tokens[0] + " не найдена");
            return true;
        }
        if (isScriptMode) System.out.println(command.getName()); // ввод названия команды в режиме скрипта
        if (command.getExpectArgs() != tokens.length-1) {
            System.out.println("Ожидалось " + command.getExpectArgs() + " аргументов, получено " + (tokens.length-1));
            return true;
        }
        try {
            command.execute(tokens);
            addCommandToHistory(command.getName());
            return true;
        } catch (IdNotFoundException e) {
            System.out.println(e.getMessage());
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат числа");
            return true;
        } catch (EndOfExecutionException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Добавление выполненной команды в историю
     * Если размер превышает 15, то первый элемент удаляется
     * @param commandName имя команды
     */
    private void addCommandToHistory(String commandName) {
        commandsHistory.add(commandName);
        logger.info("В историю команд записана новая команда {}", commandName);
        if (commandsHistory.size() > 15) {
            commandsHistory.remove(0);
        }
    }

    /**
     * Получение истории последних 15 команд
     * @return Итератор коллекции истории команд
     */
    @Override
    public Stream<String> getCommandsHistory() {
        return commandsHistory.stream();
    }

    /**
     * Получение хэш-мапы доступных команд
     */
    @Override
    public Map<String, Command> getCommandsMap() {
        return commands;
    }

    /**
     * Регистрация команды и внедрение необходимых зависимостей
     */
    @Override
    public void addCommand(Command command) {
        logger.info("Регистрация новой команды: {}", command.getName());
        Field[] fields = command.getClass().getDeclaredFields();

        for (Field field: fields) {
            if (!field.isAnnotationPresent(Inject.class)) {
                return;
            }
            try {
                field.setAccessible(true);
                Object toInject = resolveDependency(field.getType());
                if (toInject == null) {
                    continue;
                }
                field.set(command, toInject);
                logger.info("В команду {} внедрен {}", command.getName(), field.getType().getSimpleName());

            } catch (IllegalAccessException e) {
                logger.error("Не удалось внедрить зависимость в поле {}", field.getName(), e);
            }
        }
        commands.put(command.getName(), command);
    }

    private Object resolveDependency(Class<?> type) {
        return switch (type.getSimpleName()) {
            case "CollectionRepository" -> collectionManager;
            case "CommandRegistry" -> this;
            case "FileStorage" -> fileManager;
            case "UserInput", "ExecuteContext" -> reader;
            default -> null;
        };
    }
}
