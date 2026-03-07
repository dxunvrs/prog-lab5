package core;

import commands.Command;
import commands.Inject;
import exceptions.*;
import io.FileManager;
import io.InputReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.ExecutionResponse;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Менеджер для управления командами.
 * Задачи: регистрация команд, dependency injection в команды, хранение хэш-мапы доступных команд, запуск команд
 */
public class CommandManager {
    private static final Logger logger = LoggerFactory.getLogger(CommandManager.class);
    /**
     * Хэш-мап для доступных команд. Быстрый поиск за O(1), невозможность повтора
     */
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * LinkedList для истории команд, быстрое добавление в конец и удаление из начала
     */
    private final List<String> commandsHistory = new LinkedList<>();

    private final CollectionManager collectionManager;
    private final InputReader reader;
    private final FileManager fileManager;

    public CommandManager(CollectionManager collectionManager, InputReader reader, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.reader = reader;
        this.fileManager = fileManager;
    }

    /**
     * Метод первичной валидации команды, запуска ее выполнения и обработки ошибок исполнения
     * @param line введенная строка
     * @return true, если выполнение не требует остановки программы
     */
    public boolean executeCommand(String line) {
        String[] tokens = line.split(" ");
        Command command = commands.get(tokens[0]);
        if (command == null) {
            logger.warn("Пользователь ввел некорректную команду {}", tokens[0]);
            System.out.println("Команда " + tokens[0] + " не найдена");
            return true;
        }
        if (command.getExpectArgs() != tokens.length-1) {
            logger.warn("Пользователь ввел неверное количество аргументов {} для команды {}", tokens.length-1, command.getName());
            System.out.println("Ожидалось " + command.getExpectArgs() + " аргументов, получено " + (tokens.length-1));
            return true;
        }
        try {
            logger.debug("Начало выполнения команды {}", command.getName());

            ExecutionResponse executionResponse = command.execute(tokens);

            System.out.println(executionResponse.message());

            logger.info("Сообщение команды: {}", executionResponse.message());
            addCommandToHistory(command.getName());
            logger.debug("Команда {} добавлена в историю", command.getName());

            return !executionResponse.shouldExit();
        } catch (InvalidIdException | IdNotFoundException | SaveException | ScriptExecutionException e) {
            return notifyError(e);
        } catch (EndOfExecutionException e) {
            logger.info("Завершение программы", e);
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Метод для уведомления о некритичной ошибке
     * @param e класс ошибки
     * @return true для продолжения работы
     */
    private boolean notifyError(Exception e) {
        logger.error(e.getMessage(), e);
        System.out.println(e.getMessage());
        return true;
    }

    /**
     * Добавление выполненной команды в историю
     * Если размер превышает 15, то первый элемент удаляется
     * @param commandName имя команды
     */
    private void addCommandToHistory(String commandName) {
        commandsHistory.add(commandName);
        if (commandsHistory.size() > 15) {
            commandsHistory.remove(0);
        }
    }

    /**
     * Получение отформатированного списка команд
     */
    public String getFormattedCommandsList() {
        String result = commands.values().stream()
                .map(Command::getDescription).map(s -> "  " + s).collect(Collectors.joining("\n"));
        return "Список команд и их описание:" + "\n" + result;
    }

    /**
     * Получение отформатированной истории команд
     */
    public String getFormattedHistory() {
        AtomicInteger index = new AtomicInteger(1);
        String result = commandsHistory.stream().map(command -> "  " + index.getAndIncrement() + ". " + command)
                .collect(Collectors.joining("\n"));
        return "Последние 15 команд:" + "\n" + result;
    }

    /**
     * Регистрация команды и внедрение необходимых зависимостей
     */
    public void addCommand(Command command) {
        logger.debug("Регистрация новой команды: {}", command.getName());
        Field[] fields = command.getClass().getDeclaredFields();

        for (Field field: fields) {
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }
            try {
                field.setAccessible(true);
                Object toInject = resolveDependency(field.getType());
                if (toInject == null) {
                    continue;
                }
                field.set(command, toInject);
                logger.debug("В команду {} внедрен {}", command.getName(), field.getType().getSimpleName());

            } catch (IllegalAccessException e) {
                logger.error("Не удалось внедрить зависимость в поле {}", field.getName(), e);
            }
        }
        commands.put(command.getName(), command);
        logger.info("Команда {} зарегистрирована", command.getName());
    }

    /**
     * Решение зависимости
     */
    private Object resolveDependency(Class<?> type) {
        return switch (type.getSimpleName()) {
            case "CollectionManager" -> collectionManager;
            case "CommandManager" -> this;
            case "FileManager" -> fileManager;
            case "InputReader" -> reader;
            default -> null;
        };
    }
}
