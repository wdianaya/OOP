package org.example;

public enum Suit {
    // enum класс для мастей карт
    HEARTS("Червы"),
    DIAMONDS("Бубны"),
    CLUBS("Трефы"),
    SPADES("Пики");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    // вернуть название масти
    public String getSymbol() {
        return symbol;
    }
}
