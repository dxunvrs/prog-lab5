package core;

public interface CommandExecutor {
    boolean execute(String line, boolean isScriptMode);
}
