package org.example.visitor;

import org.example.expression.Add;
import org.example.expression.BinOp;
import org.example.expression.Div;
import org.example.expression.Mul;
import org.example.expression.Number;
import org.example.expression.Sub;
import org.example.expression.Variable;

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