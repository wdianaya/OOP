package ru.nsu.oop.visitor;

import ru.nsu.oop.expression.Add;
import ru.nsu.oop.expression.BinOp;
import ru.nsu.oop.expression.Div;
import ru.nsu.oop.expression.Mul;
import ru.nsu.oop.expression.Number;
import ru.nsu.oop.expression.Sub;
import ru.nsu.oop.expression.Variable;

/**
 * Класс, формирующий строки для вывода в формате:
 * бинарные операции всегда в скобках,
 * константы и переменные - без скобок.
 */
public final class PrintVisitor implements ExpressionVisitor<String> {

    @Override
    public String visitNumber(Number number) {
        return Integer.toString(number.getValue());
    }

    @Override
    public String visitVariable(Variable variable) {
        return variable.getName();
    }

    @Override
    public String visitAdd(Add add) {
        return printBinary(add);
    }

    @Override
    public String visitSub(Sub sub) {
        return printBinary(sub);
    }

    @Override
    public String visitMul(Mul mul) {
        return printBinary(mul);
    }

    @Override
    public String visitDiv(Div div) {
        return printBinary(div);
    }

    private String printBinary(BinOp op) {
        return "(" + op.getLeft().accept(this)
                + op.getSymbol()
                + op.getRight().accept(this) + ")";
    }
}