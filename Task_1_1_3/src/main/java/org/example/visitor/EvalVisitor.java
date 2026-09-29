package org.example.visitor;

import org.example.expression.Add;
import org.example.expression.Div;
import org.example.expression.Mul;
import org.example.expression.Number;
import org.example.expression.Sub;
import org.example.expression.Variable;

import java.util.Map;

/**
 * Класс, вычисляющий значние выражения.
 */
public final class EvalVisitor implements ExpressionVisitor<Integer> {
    private final Map<String, Integer> values;

    public EvalVisitor(Map<String, Integer> values) {
        if (values == null) throw new NullPointerException("values");
        this.values = values;
    }

    @Override
    public Integer visitNumber(Number number) {
        return number.getValue();
    }

    @Override
    public Integer visitVariable(Variable variable) {
        Integer value = values.get(variable.getName());
        if (value == null) {
            throw new IllegalArgumentException(
                    "no value for variable: " + variable.getName());
        }
        return value;
    }

    @Override
    public Integer visitAdd(Add add) {
        return add.getLeft().accept(this) + add.getRight().accept(this);
    }

    @Override
    public Integer visitSub(Sub sub) {
        return sub.getLeft().accept(this) - sub.getRight().accept(this);
    }

    @Override
    public Integer visitMul(Mul mul) {
        return mul.getLeft().accept(this) * mul.getRight().accept(this);
    }

    @Override
    public Integer visitDiv(Div div) {
        return div.getLeft().accept(this) / div.getRight().accept(this);
    }
}
