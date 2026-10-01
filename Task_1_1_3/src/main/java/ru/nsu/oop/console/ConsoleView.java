package ru.nsu.oop.console;

import java.util.Scanner;

import ru.nsu.oop.expression.Add;
import ru.nsu.oop.expression.Div;
import ru.nsu.oop.expression.Expression;
import ru.nsu.oop.expression.Mul;
import ru.nsu.oop.expression.Number;
import ru.nsu.oop.expression.Sub;
import ru.nsu.oop.expression.Variable;
import ru.nsu.oop.parser.ExpressionParser;

/**
 * Консольный интерфейс. Отвечает только за ввод/вывод
 * и вызовы модели.
 */
public class ConsoleView {

    public void start() {
        fromTask();
        userMode();
    }

    private void fromTask() {
        System.out.println("\nПример из условия задания:\n\n");

        Expression e = new Sub(
                new Add(
                        new Number(3),
                        new Mul(
                                new Number(2),
                                new Variable("x"))),
                new Div(
                        new Sub(
                                new Variable("y"),
                                new Number(1)),
                        new Number(5)));

        System.out.println("Вывод выражения");
        System.out.println("e.print() -> " + e.print() + "\n");

        System.out.println("Нахождение производной по переменной");
        Expression de = e.derivative("x");
        System.out.println("e.derivative(\"x\") -> " + de.print() + "\n");

        System.out.println("Вычисление значения выражения при означивании");
        int res = e.eval("x = 10; y = 5");
        System.out.println("e.eval(\"x = 10; y = 5\") -> " + res + "\n");
    }

    private void userMode() {
        System.out.println("====================================");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите выражение или 'exit' для выхода.");
            System.out.print("> ");
            String line = scanner.nextLine().trim();

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
                String var = scanner.nextLine().trim();
                System.out.println("d/d" + var + " = " + e.derivative(var).print());

                System.out.print("Введите означивание (например, x = 10; y = 13): ");
                String assigns = scanner.nextLine().trim();
                System.out.println("eval  = " + e.eval(assigns));
            } catch (RuntimeException ex) {
                System.out.println("Ошибка: " + ex.getMessage());
            }
        }
    }
}
