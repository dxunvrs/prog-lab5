package io;

import exceptions.EndOfInputException;
import exceptions.ScriptExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Класс для чтения строк из консоли/скрипта
 */
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

    /**
     * История выполнения скриптов в рамках одной команды execute_script
     */
    private final Set<String> pathHistory = new HashSet<>();

    /**
     * Очередь источников для выполнения
     */
    private final Deque<ScriptSource> sourceDeque = new ArrayDeque<>();

    public InputReader() {
        sourceDeque.push(new ScriptSource(new Scanner(System.in), null));
    }

    /**
     * Добавление скрипта в очередь
     */
    public void enqueueScript(String fileName) throws FileNotFoundException {
        if (pathHistory.contains(fileName)) {
            throw new ScriptExecutionException("Обнаружена рекурсия, файл: " + fileName);
        }
        sourceDeque.push(new ScriptSource(new Scanner(new File(fileName)),fileName));

        pathHistory.add(fileName);
        logger.debug("В очередь добавлен новый скрипт {}", fileName);
    }

    /**
     * Получение следующей строки
     * @param prompt шаблон для начала строки
     */
    public String readNextLine(String prompt) {
        while (!sourceDeque.isEmpty()) {
            System.out.print(prompt);

            ScriptSource currentSource = sourceDeque.peek();
            Scanner currentScanner = Objects.requireNonNull(currentSource).scanner;
            if (currentScanner.hasNextLine()) {
                return currentScanner.nextLine();
            }
            if (sourceDeque.size() > 1) {
                currentScanner.close();
                ScriptSource finishedSource = sourceDeque.pop();
                pathHistory.remove(finishedSource.fileName);

                logger.info("Скрипт {} завершен, возвращение к предыдущему источнику", finishedSource.fileName);
                System.out.println("Конец выполнения скрипта " + finishedSource.fileName);
                continue;
            }
            sourceDeque.pop();
            throw new EndOfInputException("Конец ввода");
        }
        throw new EndOfInputException("Чтение из пустой очереди");
    }

    /**
     * Получение режима работы программы
     * @return true, если программа выполняет скрипт, иначе - false
     */
    public boolean isScriptMode() {
        return sourceDeque.size() != 1;
    }
}
