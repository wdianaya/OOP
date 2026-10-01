package ru.nsu.oop.expression;

import ru.nsu.oop.parser.AssignmentParser;
import ru.nsu.oop.visitor.DerivativeVisitor;
import ru.nsu.oop.visitor.EvalVisitor;
import ru.nsu.oop.visitor.ExpressionVisitor;
import ru.nsu.oop.visitor.PrintVisitor;


/**
 * Класс, реализующий базовое выражение.
 */
public abstract class Expression {
    /**
     * Точка входа для Visitor.
     *
     */
    public abstract <R> R accept(ExpressionVisitor<R> visitor);

    /**
     * Печать выражения в строку.
     *
     */
    public final String print() {
        return accept(new PrintVisitor());
    }

    /**
     * Вычисление значения по строке означивания,
     * например, "x = 10; y = 13".
     *
     */
    public final int eval(String assignments) {
        return accept(new EvalVisitor(AssignmentParser.parse(assignments)));
    }

    /**
     * Символьное дифференцирование. Возвращает новое выражение.
     *
     */
    public final Expression derivative(String variable) {
        return accept(new DerivativeVisitor(variable));
    }
}
