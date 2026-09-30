package org.example.parser;

/**
 * Класс, реализующий работу лексера (tokenizer).
 * Читает строку, выдаёт токен.
 * Поддерживает два режима:
 * readToken() - прочитать токен и сдвинуть позицию.
 * peekToken() - подсмотреть следующий токен без сдвига.
 * Пробелы игнорируются.
 */
public final class Lexer {
    private final String input;
    private int pos;

    public Lexer(String input) {
        this.input = input;
        this.pos = 0;
    }

    /**
     * Прочитать следующий токен и сдвинуть позицию.
     */
    public Token readToken() throws ParseException {
        skipSpace();

        if (pos >= input.length()) {
            return new Token(Token.TokenType.EOF, "", pos);
        }

        char c = input.charAt(pos);
        int start = pos;

        switch (c) {
            case '+':
                pos++;
                return new Token(Token.TokenType.PLUS, "+", start);
            case '-':
                pos++;
                return new Token(Token.TokenType.MINUS, "-", start);
            case '*':
                pos++;
                return new Token(Token.TokenType.STAR, "*", start);
            case '/':
                pos++;
                return new Token(Token.TokenType.SLASH, "/", start);
            case '(':
                pos++;
                return new Token(Token.TokenType.LPAREN, "(", start);
            case ')':
                pos++;
                return new Token(Token.TokenType.RPAREN, ")", start);
            default:
                break;
        }

        if (Character.isDigit(c)) {
            while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                pos++;
            }

            return new Token(Token.TokenType.NUMBER,
                    input.substring(start, pos),
                    start);
        }

        if (Character.isLetter(c) || c == '_') {
            while (pos < input.length()
                    && (Character.isLetterOrDigit(input.charAt(pos))
                    || input.charAt(pos) == '_')) {
                pos++;
            }

            return new Token(Token.TokenType.VARIABLE,
                    input.substring(start, pos),
                    start);
        }

        throw new ParseException("Unexpected char symbol " + c
                + "on pos " + pos);
    }

    /**
     * Подсмотреть следующий токен, не сдвигая позицию.
     */
    public Token peekToken() throws ParseException {
        int saved = pos;
        try {
            return readToken();
        } finally {
            pos = saved;
        }
    }

    private void skipSpace() {
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
    }
}
