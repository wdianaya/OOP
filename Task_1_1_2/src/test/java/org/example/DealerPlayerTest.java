package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class DealerPlayerTest {

    @Test
    void testCalculateScoreWithoutAces() {
        Gambler gambler = new Gambler();
        gambler.takeCard(Rank.TEN, Suit.HEARTS, true);
        gambler.takeCard(Rank.SEVEN, Suit.SPADES, true);
        assertEquals(17, gambler.getFullSum());
    }

    @Test
    void testAcesConversionWhenBusted() {
        Gambler gambler = new Gambler();
        gambler.takeCard(Rank.ACE, Suit.HEARTS, true); // 11
        gambler.takeCard(Rank.NINE, Suit.DIAMONDS, true); // 9 (sum 20)
        assertEquals(20, gambler.getFullSum());
        // sum 25 -> туз пересчитывается в 1 -> 1 + 9 + 5 = 15
        gambler.takeCard(Rank.FIVE, Suit.CLUBS, true);
        assertEquals(15, gambler.getFullSum());
    }

    @Test
    void testVisibleSumWithClosedCard() {
        Dealer dealer = new Dealer();
        dealer.takeCard(Rank.TEN, Suit.HEARTS, true);
        dealer.takeCard(Rank.FIVE, Suit.DIAMONDS, false); // закрытая

        assertEquals(10, dealer.getVisibleSum());
        assertEquals(15, dealer.getFullSum());
    }

    @Test
    void testTakeOpen() {
        Dealer dealer = new Dealer();
        dealer.takeCard(Rank.TEN, Suit.HEARTS, true);
        dealer.takeCard(Rank.FIVE, Suit.DIAMONDS, false);

        List<Object> opened = dealer.takeOpen();
        assertFalse(opened.isEmpty());
        assertEquals(15, dealer.getVisibleSum());
        assertEquals(15, dealer.getFullSum());

        // второй вызов, когда закрытых нет
        List<Object> emptyOpened = dealer.takeOpen();
        assertTrue(emptyOpened.isEmpty());
    }

    @Test
    void testShouldTakeCard() {
        Dealer dealer = new Dealer();
        dealer.takeCard(Rank.TEN, Suit.HEARTS, true);
        dealer.takeCard(Rank.SIX, Suit.SPADES, true);
        assertTrue(dealer.shouldTakeCard()); // 16 < 17

        dealer.takeCard(Rank.ACE, Suit.CLUBS, true); // 16 + 11 = 27 -> туз в 1 -> 17
        assertFalse(dealer.shouldTakeCard()); // 17 не < 17
    }

    @Test
    void testPrintCard() {
        Gambler gambler = new Gambler();
        gambler.takeCard(Rank.TEN, Suit.HEARTS, true);
        String printed = gambler.printCard(false);
        assertTrue(printed.contains("Десятка Червы (10)"));
    }
}