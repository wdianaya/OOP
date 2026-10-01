package ru.nsu.oop.expression;

import ru.nsu.oop.visitor.ExpressionVisitor;

/**
 * Класс реализации деления чисел.
 */
public class Div extends BinOp {
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "/";
    }

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visitDiv(this);
    }
}
