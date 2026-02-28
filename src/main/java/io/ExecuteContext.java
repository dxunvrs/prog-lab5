package io;

import core.CommandExecutor;

/**
 * Получение параметров работы программы
 */
public interface ExecuteContext {
    void refreshInput();
    boolean isScriptMode();
    void addScriptCount();
    void subScriptCount();
    void setCommandExecutor(CommandExecutor commandExecutor);
}
