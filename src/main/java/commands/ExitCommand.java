package commands;

import exceptions.ExitException;

/**
 * Команда для выхода
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "exit - завершить программу (без сохранения в файл)", 0);
    }

    @Override
    public void execute(String[] tokens) {
        throw new ExitException("Завершение программы...");
    }
}
