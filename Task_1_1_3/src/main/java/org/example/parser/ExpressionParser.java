package org.example.parser;

import org.example.expression.Add;
import org.example.expression.Div;
import org.example.expression.Expression;
import org.example.expression.Mul;
import org.example.expression.Number;
import org.example.expression.Sub;
import org.example.expression.Variable;

/**
 * Класс, реализующий парсинг вводимого выражения.
 */
public final class ExpressionParser {
    private final Lexer lexer;

    public ExpressionParser(String input) {
        this.lexer = new Lexer(input);
    }

    public static Expression parse(String input) {
        ExpressionParser parser = new ExpressionParser(input);
        Expression result = parser.parseExpression();
        Token tail = parser.lexer.peekToken();
        if (tail.type() != Token.TokenType.EOF) {
            throw new ParseException("Unexpected token " + tail.text()
                    + " at pos " + tail.position());
        }
        return result;
    }

    // Expr ::= Number | Variable | '(' Expr op Expr ')'
    private Expression parseExpression() {
        Token token = lexer.peekToken();

        switch (token.type()) {
            case NUMBER:
                return parseNumber();
            case VARIABLE:
                return parseVariable();
            case LPAREN:
                return parseBinOp();
            default:
                throw new ParseException(
                        "Unexpected token " + token.text()
                                + " at pos " + token.position());

        }
    }

    // Atom ::= Number
    private Expression parseNumber() {
        Token token = lexer.readToken();

        try {
            return new Number(Integer.parseInt(token.text()));
        } catch (NumberFormatException e) {
            throw new ParseException(
                    "Invalid number " + token.text() + "at pos "
                            + token.position());
        }
    }

    // Atom ::= Variable
    private Expression parseVariable() {
        Token token = lexer.readToken();
        return new Variable(token.text());
    }

    // '(' Expr op Expr ')'
    private Expression parseBinOp() {
        expect(Token.TokenType.LPAREN);

        Expression left = parseExpression();
        Token op = lexer.readToken();
        Expression right = parseExpression();

        expect(Token.TokenType.RPAREN);

        switch (op.type()) {
            case PLUS:
                return new Add(left, right);
            case MINUS:
                return new Sub(left, right);
            case STAR:
                return new Mul(left, right);
            case SLASH:
                return new Div(left, right);
            default:
                throw new ParseException("Unexpected token. Got " + op.text());
        }

    }

    private void expect(Token.TokenType expected) {
        Token token = lexer.readToken();
        if (token.type() != expected) {
            throw new ParseException(
                    "Expected " + expected + ", got " + token.text()
                            + " at pos " + token.position());
        }
    }
}
