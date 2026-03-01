package io;

/**
 * Интерфейс для взаимодействия с Reader
 */
public interface UserInput {
    String readNextLine(String prompt);
    void interactive();
}

