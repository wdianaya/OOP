package org.example.cards;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    void testDefaultConstructor() {
        // Проверяем, что конструктор по умолчанию с рандомным количеством колод работает
        Deck deck = new Deck();
        assertNotNull(deck);

        // Можем взять первую карту, чтобы убедиться, что колода не пуста
        Object[] card = deck.giveCard();
        assertNotNull(card);
        assertNotNull(card[0]); // (Suit)
        assertNotNull(card[1]); // (Rank)
    }

    @Test
    void testParameterizedConstructorAndGiveCard() {
        // Создаем ровно 1 стандартную колоду (52 карты)
        Deck deck = new Deck(1);

        // Проверяем последовательную выдачу всех 52 карт
        for (int i = 0; i < 52; i++) {
            Object[] card = deck.giveCard();
            assertNotNull(card);
            assertTrue(card[0] instanceof Suit);
            assertTrue(card[1] instanceof Rank);
        }
    }

    @Test
    void testDeckEmptyException() {
        // Создаем колоду из 1 пачки
        Deck deck = new Deck(1);

        // Раздаем все 52 карты, опустошая колоду
        for (int i = 0; i < 52; i++) {
            deck.giveCard();
        }

        // Проверяем, что при попытке взять 53-ю карту выбросится IllegalStateException
        assertThrows(IllegalStateException.class, deck::giveCard);
    }
}