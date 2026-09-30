package org.example.visitor;

import org.example.expression.Add;
import org.example.expression.Div;
import org.example.expression.Expression;
import org.example.expression.Mul;
import org.example.expression.Number;
import org.example.expression.Sub;
import org.example.expression.Variable;

/**
 * Класс, реализующий символьное дифференцирование.
 */
public final class DerivativeVisitor implements ExpressionVisitor<Expression> {
    private final String variable;

    public DerivativeVisitor(String variable) {
        this.variable = variable;
    }

    @Override
    public Expression visitNumber(Number number) {
        return new Number(0);
    }

    @Override
    public Expression visitVariable(Variable variable) {
        if (variable.getName().equals(this.variable)) {
            return new Number(1);
        }
        return new Number(0);
    }

    @Override
    public Expression visitAdd(Add add) {
        return new Add(
                add.getLeft().accept(this),
                add.getRight().accept(this));
    }

    @Override
    public Expression visitSub(Sub sub) {
        return new Sub(
                sub.getLeft().accept(this),
                sub.getRight().accept(this));
    }

    @Override
    public Expression visitMul(Mul mul) {
        Expression a = mul.getLeft();
        Expression b = mul.getRight();
        Expression da = a.accept(this);
        Expression db = b.accept(this);
        // da*b + a*db
        return new Add(new Mul(da, b), new Mul(a, db));
    }

    @Override
    public Expression visitDiv(Div div) {
        Expression a = div.getLeft();
        Expression b = div.getRight();
        Expression da = a.accept(this);
        Expression db = b.accept(this);
        // (da*b - a*db) / (b*b)
        return new Div(
                new Sub(new Mul(da, b), new Mul(a, db)),
                new Mul(b, b));
    }
}
