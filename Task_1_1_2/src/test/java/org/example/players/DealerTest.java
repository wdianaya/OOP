package org.example.players;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.example.cards.Deck;
import org.example.cards.Rank;
import org.example.cards.Suit;
import org.example.utils.Utils;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void testShouldTakeCard() {
        Dealer dealer = new Dealer();

        // Сумма меньше 17 -> должен брать карту
        dealer.takeCard(Rank.SIX, Suit.HEARTS, true);
        dealer.takeCard(Rank.TEN, Suit.SPADES, true); // Сумма 16
        assertTrue(dealer.shouldTakeCard());

        // Добавляем карту, сумма становится 17 или больше -> не должен брать
        dealer.takeCard(Rank.ACE, Suit.DIAMONDS, true); // Сумма 17
        assertFalse(dealer.shouldTakeCard());
    }

    @Test
    void testTakeOpenCard() {
        Dealer dealer = new Dealer();

        // Даем закрытую карту
        dealer.takeCard(Rank.KING, Suit.CLUBS, false);

        // Открываем её
        List<Object> openCard = dealer.takeOpen();

        assertFalse(openCard.isEmpty());
        assertEquals(Rank.KING, openCard.get(0));
        assertEquals(Suit.CLUBS, openCard.get(1));
        assertEquals(true, openCard.get(2));

        // Повторный вызов должен вернуть пустой список, так как закрытых карт больше нет
        List<Object> emptyCheck = dealer.takeOpen();
        assertTrue(emptyCheck.isEmpty());
    }

    @Test
    void testDealerActions() {
        Dealer dealer = new Dealer();
        Gambler gambler = new Gambler();
        Deck deck = new Deck(1); // Используем управляемую колоду

        // Даем дилеру видимую и закрытую карту
        dealer.takeCard(Rank.FIVE, Suit.HEARTS, true);  // Видимая сумма = 5
        dealer.takeCard(Rank.SIX, Suit.SPADES, false);  // Закрытая сумма = 6 (полная = 11)

        // Запускаем ход дилера
        Utils result = dealer.dealerActions(dealer, gambler, deck);

        // Дилер должен добирать карты, пока сумма < 17, и вернуть результат
        // Проверяем, что метод завершился успешно и вернул какой-то статус (CONT или FAIL)
        assertTrue(result == Utils.CONT || result == Utils.FAIL || result == Utils.WIN);
        // Проверяем, что дилер набрал карты и его сумма теперь не меньше 17 (или у него перебор)
        assertTrue(dealer.getFullSum() >= 17 || result == Utils.FAIL);
    }
}