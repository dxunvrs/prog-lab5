package io;

import exceptions.EndOfInputException;
import exceptions.ScriptExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InputReader {
    private static class ScriptSource {
        Scanner scanner;
        String fileName;

        ScriptSource(Scanner scanner, String fileName) {
            this.scanner = scanner;
            this.fileName = fileName;
        }
    }

    private final Logger logger = LoggerFactory.getLogger(InputReader.class);
    private final Deque<ScriptSource> sources = new ArrayDeque<>();
    private final Set<String> pathHistory = new HashSet<>();

    public InputReader() {
        sources.push(
                new ScriptSource(new Scanner(System.in), null)
        );
    }

    public void enqueueScript(String fileName) throws FileNotFoundException {
        if (pathHistory.contains(fileName)) {
            throw new ScriptExecutionException("Обнаружена рекурсия, файл: " + fileName);
        }
        sources.push(
                new ScriptSource(new Scanner(new File(fileName)), fileName)
        );
        pathHistory.add(fileName);
        logger.info("В очередь добавлен новый скрипт {}", fileName);
        System.out.println("Начало выполнения скрипта " + fileName);
    }

    public String readNextLine(String prompt) {
        while (!sources.isEmpty()) {
            System.out.print(prompt);

            ScriptSource currentSource = sources.peek();
            Scanner currentScanner = Objects.requireNonNull(currentSource).scanner;

            if (currentScanner.hasNextLine()) {
                return currentScanner.nextLine();
            }
            if (sources.size() > 1) {
                currentScanner.close();
                ScriptSource finished = sources.pop();
                pathHistory.remove(finished.fileName);

                logger.info("Скрипт {} завершен, возвращение к предыдущему источнику", finished.fileName);
                System.out.println("Конец выполнения скрипта " + finished.fileName);
                continue;
            }
            sources.pop();
            throw new EndOfInputException("Конец ввода");
        }
        throw new EndOfInputException("Чтение из пустой очереди");
    }

    public boolean isScriptMode() {
        return sources.size() != 1;
    }
}
