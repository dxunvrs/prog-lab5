package io;

import commands.Command;
import core.CommandRegistry;

import java.util.Scanner;

public class ConsoleReader implements UserInput {
    private final CommandRegistry commandManager;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleReader(CommandRegistry commandManager) {
        this.commandManager = commandManager;
    }

    public void interactive() {
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                System.out.println("Конец ввода. Завершение программы...");
                System.exit(0);
            }
            String line = scanner.nextLine().trim().replaceAll("\\s+", " ");
            String[] tokens = line.split(" ");
            Command command = commandManager.getCommandsMap().get(tokens[0]);
            if (command != null) {
                command.execute(tokens);
                commandManager.addCommandToHistory(command.getName());
            } else {
                System.out.println("Нет такой команды " + tokens[0]);
            }
        }
    }

    @Override
    public String readNextLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim().replaceAll("\\s+", " ");
    }
}
