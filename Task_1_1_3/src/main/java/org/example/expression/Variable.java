package org.example.expression;

import org.example.visitor.ExpressionVisitor;

/**
 * Класс реализации переменной.
 */
public class Variable extends Expression {
    private final String name;

    /**
     * Конструктор класса переменной.
     */
    public Variable(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Variable name must not be empty");
        }
        this.name = name;
    }

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visitVariable(this);
    }

    public String getName() {
        return name;
    }
}
