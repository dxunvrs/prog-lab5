package core;

/**
 * Интерфейс для исполнения команд
 */
public interface CommandExecutor {
    boolean execute(String line, boolean isScriptMode);
}
