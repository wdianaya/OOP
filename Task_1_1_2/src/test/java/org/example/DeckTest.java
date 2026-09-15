package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void testDeckSizeAndGiveCard() {
        Deck deck = new Deck(1); // 52 карты
        Object[] card = deck.giveCard();
        assertNotNull(card);
        assertEquals(2, card.length);
    }

    @Test
    void testMultiDeckSize() {
        Deck deck = new Deck(3); // 3 * 52 = 156 карт
        assertNotNull(deck);
    }

    @Test
    void testEmptyDeckThrowsException() {
        Deck deck = new Deck(1);
        for (int i = 0; i < 52; i++) {
            deck.giveCard();
        }
        assertThrows(IllegalStateException.class, deck::giveCard);
    }
}