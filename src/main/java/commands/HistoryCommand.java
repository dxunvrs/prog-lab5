package commands;

import core.CommandRegistry;

import java.util.Iterator;

public class HistoryCommand extends Command {
    private final CommandRegistry commandManager;

    public HistoryCommand(CommandRegistry commandManager) {
        super("history", "history - вывести последние 15 команд без аргументов", 0);
        this.commandManager = commandManager;
    }

    @Override
    protected void process() {
        Iterator<String> iterator = commandManager.getCommandsHistory();
        int i = 1;
        while (iterator.hasNext()) {
            System.out.println(i++ + ". " + iterator.next());
        }
    }
}
