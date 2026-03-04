package commands;

import utility.ExecutionResponse;

/**
 * Базовый класс для команды
 */
public abstract class Command {
    private final String name;
    private final String description;

    /**
     * Число ожидаемых аргументов
     */
    private final int expectArgs;

    public Command(String name, String description, int expectArgs) {
        this.name = name;
        this.description = description;
        this.expectArgs = expectArgs;
    }

    /**
     * Метод выполнения команды
     *
     * @return Ответ, состоящий из сообщения выполнения и флага для выхода
     */
    public abstract ExecutionResponse execute(String[] tokens);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getExpectArgs() { return expectArgs; }
}