package commands;

import exceptions.ScriptExecutionException;
import io.InputReader;
import utility.ExecutionResponse;

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
    public ExecutionResponse execute(String[] tokens) {
        try {
            inputReader.enqueueScript(tokens[1]);
            return new ExecutionResponse("Начало выполнения скрипта " + tokens[1], false);
        } catch (IOException e) {
            throw new ScriptExecutionException("Ошибка чтения " + e.getMessage());
        }
    }
}
