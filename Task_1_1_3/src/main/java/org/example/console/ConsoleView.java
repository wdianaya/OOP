package org.example.console;

import org.example.expression.Add;
import org.example.expression.Div;
import org.example.expression.Expression;
import org.example.expression.Mul;
import org.example.expression.Number;
import org.example.expression.Sub;
import org.example.expression.Variable;
import org.example.parser.ExpressionParser;

import java.util.Scanner;

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

        Expression e = new Add(
                new Number(3),
                new Mul(
                        new Number(2),
                        new Variable("xa"))
        ); // (3+(2*x))

        System.out.println("Исходная запись");
        System.out.println("Expression e = new Add(" +
                "new Number(3)," +
                "new Mul(" +
                "new Number(2)," +
                "new Variable(\"xa\"))" +
                ")\n");
        System.out.println("Вывод выражения");
        System.out.println("e.print() -> " + e.print() + "\n");

        System.out.println("Нахождение производной по переменной");
        Expression de = e.derivative("xa");
        System.out.println("e.derivative(\"xa\") -> " + de.print() + "\n");

        System.out.println("Вычисление значения выражения при означивании");
        int res = e.eval("xa = 10");
        System.out.println("e.eval(\"xa = 10\") -> " + res + "\n");
    }

    private void userMode() {
        System.out.println("====================================");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите выражение или 'exit' для выхода.");
            System.out.print("> ");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) continue;
            if (line.equals("exit")) break;

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
