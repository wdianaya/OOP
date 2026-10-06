package ru.nsu.oop.console;

import ru.nsu.oop.expression.Expression;
import ru.nsu.oop.parser.ExpressionParser;

/**
 * Консольный интерфейс. Отвечает только за ввод/вывод
 * и вызовы модели.
 */
public class ConsoleView {
    private final InputReader inputReader;

    /**
     * Конструктор по умолчанию.
     */
    public ConsoleView() {
        this(new ConsoleInputReader());
    }

    /**
     * Конструктор для тестов.
     */
    public ConsoleView(InputReader inputReader) {
        if (inputReader == null) {
            throw new NullPointerException("inputReader cannot be null");
        }
        this.inputReader = inputReader;
    }

    /**
     * Интерактивный ввод/вывод.
     */
    public void start() {
        ConsolePrint.printString("====================================");
        while (true) {
            ConsolePrint.printString("Введите выражение или 'exit' для выхода.");
            ConsolePrint.printString("> ");

            String line = inputReader.readLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equals("exit")) {
                break;
            }
            try {
                Expression e = ExpressionParser.parse(line);
                ConsolePrint.printString("Введенное выражение = " + e.print());

                ConsolePrint.printString("Введите переменную для дифференцирования: ");
                String var = inputReader.readLine().trim();
                ConsolePrint.printString("d/d" + var + " = " + e.derivative(var).print());

                ConsolePrint.printString("Введите означивание (например, x = 10; y = 13): ");
                String assigns = inputReader.readLine().trim();
                ConsolePrint.printString("eval = " + e.eval(assigns));

            } catch (RuntimeException ex) {
                ConsolePrint.printException(ex.getMessage());
            }
        }
    }
}
