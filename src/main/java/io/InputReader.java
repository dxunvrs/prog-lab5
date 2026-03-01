package io;

import core.CommandExecutor;
import exceptions.EndOfInputException;
import exceptions.ScriptExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InputReader implements ExecuteContext, UserInput {
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
    private CommandExecutor commandExecutor;
    private boolean isWorking = true;

    public InputReader() {
        sources.push(
                new ScriptSource(new Scanner(System.in), null)
        );
    }

    @Override
    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Override
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

    @Override
    public String readNextLine(String prompt) {
        while (!sources.isEmpty()) {
            System.out.print(prompt);

            ScriptSource currentSource = sources.peek();
            assert currentSource != null;
            Scanner currentScanner = currentSource.scanner;

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

    @Override
    public void interactive() {
        while (isWorking) {
            try {
                String line = readNextLine("> ");
                String formattedLine = line.trim().replaceAll("\\s+", " ");

                if (formattedLine.isEmpty()) continue;

                if (!commandExecutor.execute(formattedLine, isScriptMode())) {
                    isWorking = false;
                }
            } catch (EndOfInputException e) {
                System.out.println(e.getMessage());
                isWorking = false;
            }
        }
    }

    @Override
    public boolean isScriptMode() {
        return sources.size() != 1;
    }
}
