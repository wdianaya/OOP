package ru.nsu.oop.expression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {
    Expression e = new Sub(
            new Add(
                    new Number(3),
                    new Mul(
                            new Number(2),
                            new Variable("x"))),
            new Div(
                    new Sub(
                            new Variable("y"),
                            new Number(1)),

                    new Number(5)));

    @Test
    void printFullExpression() {
        assertEquals("((3+(2*x))-((y-1)/5))", e.print());
    }

    @Test
    void evalCorrectResult() {
        assertEquals(23, e.eval("x=10;y=5"));
    }

    @Test
    void derivativeCorrectResult() {
        assertEquals("((0+((0*x)+(2*1)))-((((0-0)*5)-((y-1)*0))/(5*5)))",
                e.derivative("x").print());
    }

    @Test
    void evalCorrectResultWithExtraVar() {
        assertEquals(23, e.eval("x=10;y=5;z=4"));
    }
}