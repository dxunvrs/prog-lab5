package io;

import core.CommandRegistry;

public interface UserInput {
    String readNextLine(String prompt);
    void interactive(CommandRegistry commandManager);
}
