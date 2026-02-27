package commands;

import exceptions.ScriptExecutionException;
import io.ExecuteContext;
import io.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Команда для исполнения скрипта
 */
public class ExecuteScriptCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteScriptCommand.class);

    @Inject
    private ExecuteContext executeContext;

    @Inject
    private UserInput userInput;

    /**
     * HashSet для хранения имен исполняемых файлов.
     * Предотвращает возможный цикл
     */
    private final Set<String> scriptStack = new HashSet<>();

    public ExecuteScriptCommand() {
        super("execute_script", "execute_script - считать и исполнить скрипт из файла", 1);
    }

    /**
     * Выполнение команды: проверка на рекурсию, замена потока System.in на новый и запуск интерактивного режима с него.
     * В конце устанавливает исходный поток и обновляет его в Reader
     */
    @Override
    public void execute(String[] tokens) {
        String fileName = tokens[1];
        if (scriptStack.contains(fileName)) {
            logger.error("В скрипте рекурсия");
            System.out.println("В скрипте обнаружена рекурсия");
            return;
        }
        scriptStack.add(fileName);
        InputStream originalIn = System.in;

        try (InputStream fileIn = new FileInputStream(fileName)) {
            System.setIn(fileIn);
            logger.info("Начало выполнение скрипта");
            System.out.println("Выполнение скрипта: ");
            executeContext.addScriptCount();
            userInput.interactive();
        } catch (ScriptExecutionException e) {
            System.out.println(e.getMessage());
            logger.error("Конец ввода", e);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            logger.error("Ошибка: файл не найден", e);
        } catch (SecurityException e) {
            System.out.println("Недостаточно прав");
            logger.error("Ошибка: недостаточно прав", e);
        } catch (IOException e) {
            System.out.println("Ошибка IO");
            logger.error("Ошибка IO", e);
        } finally {
            System.setIn(originalIn);
            executeContext.refreshInput();
            executeContext.subScriptCount();

            scriptStack.remove(fileName);
            logger.info("Скрипт завершен");
            System.out.println("Выполнение скрипта завершено");
        }
    }
}
