package commands;

import utility.ExecutionResponse;

/**
 * Команда для выхода
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "exit - завершить программу (без сохранения в файл)", 0);
    }

    @Override
    public ExecutionResponse execute(String[] tokens) {
        return new ExecutionResponse("Завершение программы...", true);
    }
}
