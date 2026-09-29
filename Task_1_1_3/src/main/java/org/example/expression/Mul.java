package org.example.expression;

import org.example.visitor.ExpressionVisitor;

public class Mul extends BinOp {
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visitMul(this);
    }
}
