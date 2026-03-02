package commands;

import exceptions.ScriptExecutionException;
import io.InputReader;

import java.io.IOException;


/**
 * Команда для исполнения скрипта
 */
public class ExecuteScriptCommand extends Command {
    @Inject
    private InputReader inputReader;

    public ExecuteScriptCommand() {
        super("execute_script", "execute_script - считать и исполнить скрипт из файла", 1);
    }

    @Override
    public void execute(String[] tokens) {
        try {
            inputReader.enqueueScript(tokens[1]);
        } catch (IOException e) {
            throw new ScriptExecutionException("Ошибка чтения " + e.getMessage());
        }
    }
}
