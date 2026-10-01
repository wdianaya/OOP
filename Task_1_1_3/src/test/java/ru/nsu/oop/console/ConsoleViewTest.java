package ru.nsu.oop.console;

import org.junit.jupiter.api.Test;

class ConsoleViewTest {
    @Test
    void testExitCommand() {
        // Пользователь сразу вводит "exit"
        InputReader stubInput = () -> "exit";

        ConsoleView view = new ConsoleView(stubInput);

        // Проверяем, что метод завершается без ошибок и бесконечного цикла
        view.start();
    }
}