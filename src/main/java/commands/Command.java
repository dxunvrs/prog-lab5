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
            process();
        } else {
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