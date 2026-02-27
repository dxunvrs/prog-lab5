package commands;

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
     */
    public abstract void execute(String[] tokens);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getExpectArgs() { return expectArgs; }
}