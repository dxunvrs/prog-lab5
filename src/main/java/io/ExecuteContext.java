package io;

import core.CommandExecutor;

import java.io.FileNotFoundException;

/**
 * Получение параметров работы программы
 */
public interface ExecuteContext {
    boolean isScriptMode();
    void enqueueScript(String fileName) throws FileNotFoundException;
    void setCommandExecutor(CommandExecutor commandExecutor);
}
