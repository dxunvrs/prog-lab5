package commands;

import commands.di.ReaderDependant;
import io.UserInput;

/**
 * Команда для выхода
 */
public class ExitCommand extends Command implements ReaderDependant {
    private UserInput reader;
    public ExitCommand() {
        super("exit", "exit - завершить программу (без сохранения в файл)", 0);
    }

    @Override
    protected void process() {
        reader.stopProgram();
    }

    @Override
    public void setReader(UserInput reader) {
        this.reader = reader;
    }
}
