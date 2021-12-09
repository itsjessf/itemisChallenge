package com.itemis;

import java.io.PrintStream;
import java.util.Scanner;

public class UserInputReader {

    private final Scanner scanner;
    private final PrintStream outputStream;
    private final UserInputHandler inputHandler;
    private final ResultDisplayer resultDisplayer;

    public UserInputReader(Scanner scanner, UserInputHandler inputHandler, ResultDisplayer resultDisplayer, PrintStream outputStream) {
        this.scanner = scanner;
        this.inputHandler = inputHandler;
        this.resultDisplayer = resultDisplayer;
        this.outputStream = outputStream;
    }

    public void readUserInput() {

        System.out.println("Please input your notes for Galactic profit! \nType 'exit' when you are done");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.trim().isEmpty()){
                outputStream.println("Type in your Galactic notes. Type 'exit' when you are done!");
                continue;
            }
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            inputHandler.handleUserInput();
        }
        scanner.close();
        resultDisplayer.displayResult();
    }
}
