package ru.nsu.oop.parser;

import org.junit.jupiter.api.Test;
import ru.nsu.oop.expression.Add;
import ru.nsu.oop.expression.Div;
import ru.nsu.oop.expression.Expression;
import ru.nsu.oop.expression.Mul;
import ru.nsu.oop.expression.Number;
import ru.nsu.oop.expression.Sub;
import ru.nsu.oop.expression.Variable;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {
    Expression expected = new Sub(
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
    void parserReturnsCorrectExpressionTree() {
        Expression actual = ExpressionParser.parse("((3+(2*x))-((y-1)/5))");
        assertEquals(expected.print(), actual.print());
    }

    @Test
    void parserReturnsCorrectExpressionString() {
        Expression parsed = ExpressionParser.parse("((3+(2*x))-((y-1)/5))");
        assertEquals("((3+(2*x))-((y-1)/5))", parsed.print());
    }
}