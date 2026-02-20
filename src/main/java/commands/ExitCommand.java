package commands;

/**
 * Команда для выхода
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "exit - завершить программу (без сохранения в файл)", 0);
    }

    @Override
    protected void process() {
        System.out.print("Завершение программы...");
        System.exit(0);
    }
}
