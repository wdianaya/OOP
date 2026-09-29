package org.example.expression;

import org.example.visitor.ExpressionVisitor;
import org.example.visitor.PrintVisitor;
import org.example.visitor.EvalVisitor;
import org.example.visitor.DerivativeVisitor;
import org.example.parser.AssignmentParser;

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
