package io;

import commands.Command;
import core.ICommandManager;

import java.util.Scanner;

public class ConsoleReader {
    private final ICommandManager commandManager;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleReader(ICommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void interactive() {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            Command command = commandManager.getCommandsMap().get(tokens[0]);
            if (command != null) {
                command.execute(tokens);
            } else {
                System.out.println("Нет такой команды " + tokens[0]); // пока sout, потом надо exception сделать CommandNotFound
            }
        }
    }
}
