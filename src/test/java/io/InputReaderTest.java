package io;

import exceptions.EndOfInputException;
import exceptions.ScriptExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class InputReaderTest {
    private InputReader inputReader;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() {
        inputReader = new InputReader();
    }

    @Test
    @DisplayName("Обнаружение рекурсии")
    void recursionDetection() throws IOException {
        Path file1 = tempDir.resolve("file1");
        Path file2 = tempDir.resolve("file2");
        // Path file3 = tempDir.resolve("file3");
        Files.createFile(file1);
        Files.createFile(file2);
        // Files.createFile(file3);

        // добавляем в очередь два раза file1
        inputReader.enqueueScript(file1.toString());
        inputReader.enqueueScript(file2.toString());
        // assertThrows(ScriptExecutionException.class, ()->inputReader.enqueueScript(file3.toString()));
        assertThrows(ScriptExecutionException.class, ()->inputReader.enqueueScript(file1.toString()));
    }

    @Test
    @DisplayName("Возможность запустить скрипт два раза подряд")
    void pathHistoryCleaning() throws IOException {
        Path file = tempDir.resolve("file");
        Files.writeString(file, "info");

        inputReader.enqueueScript(file.toString());

        inputReader.readNextLine(" ");
        try {
            inputReader.readNextLine(" ");
        } catch (EndOfInputException e) {
            // был конец файла, значит должен удалиться из истории
        }

        assertDoesNotThrow(() -> inputReader.enqueueScript(file.toString()));
    }
}
