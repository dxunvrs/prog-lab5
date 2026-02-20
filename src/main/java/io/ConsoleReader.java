package io;

import commands.Command;
import core.CommandRegistry;
import exceptions.EndOfInputException;

import java.util.Scanner;

public class ConsoleReader implements UserInput {
    private CommandRegistry commandManager;
    private Scanner scanner;
    private int scriptCount = 0;

    @Override
    public void interactive(CommandRegistry commandManager) {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");

            if (!scanner.hasNextLine()) {
                System.out.println("Конец ввода");
                return;
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
        if (!scanner.hasNextLine()) {
            throw new EndOfInputException("Конец ввода");
        }
        return scanner.nextLine().trim().replaceAll("\\s+", " ");
    }

    @Override
    public void refreshInput() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean isScriptMode() {
        return scriptCount != 0;
    }

    @Override
    public void addScriptCount() {
        scriptCount++;
    }

    @Override
    public void subScriptCount() {
        scriptCount--;
    }
}
