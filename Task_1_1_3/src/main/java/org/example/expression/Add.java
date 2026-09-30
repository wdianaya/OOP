package org.example.expression;

import org.example.visitor.ExpressionVisitor;

/**
 * Класс реализации сложения чисел.
 */
public class Add extends BinOp {

    public Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visitAdd(this);
    }
}
