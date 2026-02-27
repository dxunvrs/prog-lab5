package io;

import core.CommandExecutor;

public interface ExecuteContext {
    void refreshInput();
    boolean isScriptMode();
    void addScriptCount();
    void subScriptCount();
    void setCommandExecutor(CommandExecutor commandExecutor);
}
