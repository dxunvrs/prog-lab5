package commands;

import commands.di.CommandManagerDependant;
import core.CommandRegistry;

import java.util.Iterator;

/**
 * Команда для вывода последних 15 команд без аргументов
 */
public class HistoryCommand extends Command implements CommandManagerDependant {
    private CommandRegistry commandManager;

    public HistoryCommand() {
        super("history", "history - вывести последние 15 команд без аргументов", 0);
    }

    @Override
    protected void process() {
        System.out.println("Последние 15 команд: ");
        Iterator<String> iterator = commandManager.getCommandsHistory();
        int i = 1;
        while (iterator.hasNext()) {
            System.out.println(i++ + ". " + iterator.next());
        }
    }

    @Override
    public void setCommandManager(CommandRegistry commandManager) {
        this.commandManager = commandManager;
    }
}
