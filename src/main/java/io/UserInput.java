package io;

import core.CommandExecutor;

/**
 * Интерфейс для взаимодействия с {@link Reader}
 */
public interface UserInput {
    String readNextLine(String prompt);
    void interactive();
}

