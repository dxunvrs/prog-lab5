package commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Form;

/**
 * Базовый класс для команды
 */
public abstract class Command {
    private static final Logger logger = LoggerFactory.getLogger(Command.class);
    private final String name;
    private final String description;

    /**
     * Число ожидаемых аргументов
     */
    private final int expectArgs;

    /**
     * Полученные аргументы
     */
    protected String[] tokens;

    public Command(String name, String description, int expectArgs) {
        this.name = name;
        this.description = description;
        this.expectArgs = expectArgs;
    }

    /**
     * Публичный метод выполнения.
     * Производит валидацию числа принятых аргументов
     * @param tokens полученные аргументы
     */
    public void execute(String[] tokens) {
        this.tokens = tokens;
        if (expectArgs == (tokens.length - 1)) {
            logger.info("Выполнение команды: {}", name);
            process();
            logger.info("Команда {} выполнилась без ошибок", name);
        } else {
            logger.error("Для команды {} передалось неверное количество аргументов", name);
            System.out.println("Ожидалось " + expectArgs + " аргументов, получено " + (tokens.length-1));
        }
    }

    /**
     * Настоящий метод выполнения команды
     */
    protected abstract void process();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}