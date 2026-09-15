package org.example;

/**
 * Перечисление, представляющее масть игральной карты.
 */
public enum Suit {

    HEARTS("Червы"),
    DIAMONDS("Бубны"),
    CLUBS("Трефы"),
    SPADES("Пики");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Возвращает название масти.
     */
    public String getSymbol() {
        return symbol;
    }
}
