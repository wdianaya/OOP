package ru.nsu.oop.console;

import java.util.Scanner;

/**
 * Класс, ответственный за объект типа Scanner.
 */
public class ConsoleInputReader implements InputReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
