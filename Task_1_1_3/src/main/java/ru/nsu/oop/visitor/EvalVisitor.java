package ru.nsu.oop.visitor;

import java.util.Map;

import ru.nsu.oop.expression.Add;
import ru.nsu.oop.expression.Div;
import ru.nsu.oop.expression.Mul;
import ru.nsu.oop.expression.Number;
import ru.nsu.oop.expression.Sub;
import ru.nsu.oop.expression.Variable;

/**
 * Класс, вычисляющий значние выражения.
 */
public final class EvalVisitor implements ExpressionVisitor<Integer> {
    private final Map<String, Integer> values;

    /**
     * Конструктор класса, устанавливающий переменные для означивания.
     */
    public EvalVisitor(Map<String, Integer> values) {
        if (values == null) {
            throw new NullPointerException("values");
        }
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
        try {
            Integer left = add.getLeft().accept(this);
            Integer right = add.getRight().accept(this);
            return left + right;
        } catch (IllegalArgumentException ex) {
            System.out.println("Ошибка при вычислении: " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Integer visitSub(Sub sub) {

        try {
            Integer left = sub.getLeft().accept(this);
            Integer right = sub.getRight().accept(this);
            return left - right;
        } catch (IllegalArgumentException ex) {
            System.out.println("Ошибка при вычислении: " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Integer visitMul(Mul mul) {

        try {
            Integer left = mul.getLeft().accept(this);
            Integer right = mul.getRight().accept(this);
            return left * right;
        } catch (IllegalArgumentException ex) {
            System.out.println("Ошибка при вычислении: " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Integer visitDiv(Div div) {

        try {
            Integer left = div.getLeft().accept(this);
            Integer right = div.getRight().accept(this);
            return left / right;
        } catch (IllegalArgumentException ex) {
            System.out.println("Ошибка при вычислении: " + ex.getMessage());
        }
        return 0;
    }
}
