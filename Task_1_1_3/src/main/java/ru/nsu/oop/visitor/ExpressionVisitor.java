package ru.nsu.oop.visitor;

import ru.nsu.oop.expression.Add;
import ru.nsu.oop.expression.Div;
import ru.nsu.oop.expression.Mul;
import ru.nsu.oop.expression.Number;
import ru.nsu.oop.expression.Sub;
import ru.nsu.oop.expression.Variable;

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