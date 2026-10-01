package ru.nsu.oop.console;

/**
 * Класс, реализовывающий вывод в консоль.
 */
public class ConsolePrint {
    /**
     * Печать обычный строки.
     */
    public static void printString(String message) {
        System.out.println(message);
    }

    /**
     * Печать сообщения об ошибке.
     */
    public static void printException(String message) {
        System.out.println("Ошибка: " + message);
    }
}
