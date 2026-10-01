package ru.nsu.oop;


import ru.nsu.oop.console.ConsoleView;

/**
 * Точка входа в программу.
 */
public class Main {
    public static void main(String[] args) {
        ConsoleView console = new ConsoleView();
        console.start();
    }
}