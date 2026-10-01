package ru.nsu.oop.console;

import java.util.Scanner;

import ru.nsu.oop.expression.Expression;
import ru.nsu.oop.parser.ExpressionParser;

/**
 * Консольный интерфейс. Отвечает только за ввод/вывод
 * и вызовы модели.
 */
public class ConsoleView {
    private final InputReader inputReader;

    /** Конструктор по умолчанию. */
    public ConsoleView() {
        this(new ConsoleInputReader());
    }

    /** Конструктор для тестов. */
    public ConsoleView(InputReader inputReader) {
        if (inputReader == null) {
            throw new NullPointerException("inputReader cannot be null");
        }
        this.inputReader = inputReader;
    }

    public void start() {
        userMode();
    }

    private void userMode() {
        System.out.println("====================================");
        while (true) {
            System.out.println("Введите выражение или 'exit' для выхода.");
            System.out.print("> ");
            String line = inputReader.readLine().trim();

            if (line.isEmpty()) {
                continue;
            }
            if (line.equals("exit")) {
                break;
            }

            try {
                Expression e = ExpressionParser.parse(line);
                System.out.println("Введенное выражение = " + e.print());

                System.out.print("Введите переменную для дифференцирования: ");
                String var = inputReader.readLine().trim();
                System.out.println("d/d" + var + " = " + e.derivative(var).print());

                System.out.print("Введите означивание (например, x = 10; y = 13): ");
                String assigns = inputReader.readLine().trim();
                System.out.println("eval = " + e.eval(assigns));
            } catch (RuntimeException ex) {
                System.out.println("Ошибка: " + ex.getMessage());
            }
        }
    }
}
