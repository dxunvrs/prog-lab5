package io;

import core.CommandRegistry;

/**
 * Интерфейс для взаимодействия с {@link ConsoleReader}
 */
public interface UserInput {
    String readNextLine(String prompt);
    void interactive(CommandRegistry commandManager);
    void refreshInput();
    boolean isScriptMode();
    void addScriptCount();
    void subScriptCount();
}
