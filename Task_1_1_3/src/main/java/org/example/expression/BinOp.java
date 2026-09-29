package org.example.expression;

/**
 * Класс предок для всех бинарных операций.
 */
public abstract class BinOp extends Expression {
    protected final Expression left;
    protected final Expression right;

    protected BinOp(Expression left, Expression right) {
        if (left == null) throw new NullPointerException("left");
        if (right == null) throw new NullPointerException("right");
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    /**
     * Символ операции: "+", "-", "*", "/".
     *
     */
    public abstract String getSymbol();
}
