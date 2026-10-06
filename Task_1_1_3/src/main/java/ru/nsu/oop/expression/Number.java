package ru.nsu.oop.expression;

import ru.nsu.oop.visitor.ExpressionVisitor;

/**
 * Класс реализации числа.
 */
public class Number extends Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visitNumber(this);
    }
}
