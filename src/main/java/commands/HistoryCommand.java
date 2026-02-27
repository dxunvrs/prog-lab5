package commands;

import core.CommandRegistry;

import java.util.Iterator;

/**
 * Команда для вывода последних 15 команд без аргументов
 */
public class HistoryCommand extends Command {
    @Inject
    private CommandRegistry commandRegistry;

    public HistoryCommand() {
        super("history", "history - вывести последние 15 команд без аргументов", 0);
    }

    @Override
    public void execute(String[] tokens) {
        System.out.println("Последние 15 команд: ");
        Iterator<String> iterator = commandRegistry.getCommandsHistory();
        int i = 1;
        while (iterator.hasNext()) {
            System.out.println(i++ + ". " + iterator.next());
        }
    }
}
