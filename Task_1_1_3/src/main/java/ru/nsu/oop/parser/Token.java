package ru.nsu.oop.parser;

/**
 * Токен - минимальная единица текста (поступающего на вход выражения).
 */
public record Token(TokenType type, String text, int position) {

    public enum TokenType {
        NUMBER,
        VARIABLE,
        PLUS,
        MINUS,
        STAR,
        SLASH,
        LPAREN,
        RPAREN,
        EOF
    }
}