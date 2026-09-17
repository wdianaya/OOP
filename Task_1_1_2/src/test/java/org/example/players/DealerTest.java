package org.example.players;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.example.cards.Card;
import org.example.cards.Deck;
import org.example.cards.Rank;
import org.example.cards.Suit;
import org.example.utils.GameResults;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void testTakeOpenCard() {
        Dealer dealer = new Dealer();

        // Даем закрытую карту
        dealer.takeCard(Rank.KING, Suit.CLUBS, false);

        // Открываем её
        List<Card> openCard = dealer.takeOpen();

        assertFalse(openCard.isEmpty());
        assertEquals(1, openCard.size()); // проверка размера
        assertEquals(Rank.KING, openCard.get(0).rank());
        assertEquals(Suit.CLUBS, openCard.get(0).suit());
        assertTrue(openCard.get(0).isOpen());

        // Повторный вызов должен вернуть пустой список, так как закрытых карт больше нет
        List<Card> emptyCheck = dealer.takeOpen();
        assertTrue(emptyCheck.isEmpty());
    }

    @Test
    void testDealerActions() {
        Dealer dealer = new Dealer();
        Gambler gambler = new Gambler();
        Deck deck = new Deck();

        // Даем дилеру видимую и закрытую карту
        dealer.takeCard(Rank.FIVE, Suit.HEARTS, true);  // Видимая сумма = 5
        dealer.takeCard(Rank.SIX, Suit.SPADES, false);  // Закрытая сумма = 6 (полная = 11)

        // Запускаем ход дилера
        GameResults result = dealer.dealerActions(dealer, gambler, deck);

        // Дилер должен добирать карты, пока сумма < 17, и вернуть результат
        assertTrue(result == GameResults.CONT ||
                result == GameResults.FAIL || result == GameResults.WIN);
        // Проверяем, что дилер набрал карты и его сумма теперь не меньше 17 (или у него перебор)
        assertTrue(dealer.getFullSum() >= 17 || result == GameResults.FAIL);
    }
}