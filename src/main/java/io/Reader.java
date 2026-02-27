package io;

import core.CommandExecutor;
import exceptions.EndOfInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Класс для чтения ввода/скрипта
 */
public class Reader implements UserInput, ExecuteContext {
    private static final Logger logger = LoggerFactory.getLogger(Reader.class);
    private CommandExecutor commandExecutor;
    private Scanner scanner;
    private boolean isWorking = true;

    /**
     * Количество вложенных скриптов
     */
    private int scriptCount = 0;

    /**
     * Запуск интерактивного чтения с System.in и выполнение команд
     */
    @Override
    public void interactive() {
        scanner = new Scanner(System.in);
        while (isWorking) {
            System.out.print("> ");

            if (!scanner.hasNextLine()) {
                System.out.println("Конец ввода");
                return;
            }
            String line = scanner.nextLine().trim().replaceAll("\\s+", " ");
            isWorking = commandExecutor.execute(line, isScriptMode());
        }
    }

    /**
     * Метод получения одной строки ввода, используется в {@link utility.Form#ask}
     * @param prompt шаблон, с которого начинается строка
     * @return Возвращает ввод
     */
    @Override
    public String readNextLine(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            throw new EndOfInputException("Конец ввода");
        }
        return scanner.nextLine().trim().replaceAll("\\s+", " ");
    }

    /**
     * Обновление информации о потоке System.in.
     */
    @Override
    public void refreshInput() {
        logger.info("Поток ввода обновился");
        this.scanner = new Scanner(System.in);
    }

    /**
     * Получение режима работы
     * @return true, если количество вложенных скриптов 0, иначе - false
     */
    @Override
    public boolean isScriptMode() {
        return scriptCount != 0;
    }

    /**
     * Увеличение количества вложенных скриптов, вызывается после команды execute_script
     */
    @Override
    public void addScriptCount() {
        logger.info("Добавился вложенный скрипт");
        scriptCount++;
    }

    /**
     * Уменьшение количества вложенных скриптов, вызывается после завершения обработки одного скрипта (даже в случае ошибки)
     */
    @Override
    public void subScriptCount() {
        logger.info("Один вложенный скрипт завершился");
        scriptCount--;
    }

    @Override
    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }
}
