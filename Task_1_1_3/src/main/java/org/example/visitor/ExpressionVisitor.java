package org.example.visitor;

import org.example.expression.Add;
import org.example.expression.Div;
import org.example.expression.Mul;
import org.example.expression.Number;
import org.example.expression.Sub;
import org.example.expression.Variable;

/**
 * Интерфейс для обхода выражений. Параметр R - тип результата:
 * String для печати, Integer для eval, Expression для derivative.
 */
public interface ExpressionVisitor<R> {
    R visitNumber(Number number);

    R visitVariable(Variable variable);

    R visitAdd(Add add);

    R visitSub(Sub sub);

    R visitMul(Mul mul);

    R visitDiv(Div div);
}