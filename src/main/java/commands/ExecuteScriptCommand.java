package commands;

import commands.di.CommandManagerDependant;
import commands.di.ReaderDependant;
import core.CommandRegistry;
import exceptions.ScriptExecutionException;
import io.UserInput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Команда для исполнения скрипта
 */
public class ExecuteScriptCommand extends Command implements CommandManagerDependant, ReaderDependant {
    private CommandRegistry commandManager;
    private UserInput reader;

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
     * В конце устанавливает исходный поток и обновляет {@link io.ConsoleReader}
     */
    @Override
    protected void process() {
        String fileName = tokens[1];
        if (scriptStack.contains(fileName)) {
            System.out.println("В скрипте обнаружена рекурсия");
            return;
        }
        scriptStack.add(fileName);
        InputStream originalIn = System.in;

        try (InputStream fileIn = new FileInputStream(fileName)) {
            System.setIn(fileIn);
            System.out.println("Выполнение скрипта: ");
            reader.addScriptCount();
            reader.interactive(commandManager);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (SecurityException e) {
            System.out.println("Недостаточно прав");
        } catch (IOException e) {
            System.out.println("Ошибка IO");
        } catch (ScriptExecutionException e) {
            System.out.println(e.getMessage());
        } finally {
            System.setIn(originalIn);
            reader.refreshInput();
            reader.subScriptCount();

            scriptStack.remove(fileName);
            System.out.println("Выполнение скрипта завершено");
        }
    }

    @Override
    public void setCommandManager(CommandRegistry commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void setReader(UserInput reader) {
        this.reader = reader;
    }
}
