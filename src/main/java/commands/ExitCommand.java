package commands;

import exceptions.EndOfExecutionException;
import io.UserInput;

/**
 * Команда для выхода
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "exit - завершить программу (без сохранения в файл)", 0);
    }

    @Override
    public void execute(String[] tokens) {
        throw new EndOfExecutionException("Завершение программы...");
    }
}
