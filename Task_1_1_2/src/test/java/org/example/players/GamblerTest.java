package org.example.players;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.example.cards.Deck;
import org.example.cards.Rank;
import org.example.cards.Suit;
import org.example.utils.Utils;
import org.junit.jupiter.api.Test;

class GamblerTest {

    @Test
    void testGamblerActionStopImmediately() {
        Gambler gambler = new Gambler();
        Dealer dealer = new Dealer();
        Deck deck = new Deck(1);

        // Даем игроку карты на сумму 9 (состояние CONT)
        gambler.takeCard(Rank.SEVEN, Suit.HEARTS);
        gambler.takeCard(Rank.TWO, Suit.SPADES);

        // Передаем сканер с вводом "0" (остановиться сразу)
        Scanner scanner = new Scanner("0\n");

        Utils result = gambler.gamblerAction(gambler, dealer, scanner, deck);
        assertEquals(Utils.CONT, result);
    }

    @Test
    void testGamblerActionTakeCardAndStop() {
        Gambler gambler = new Gambler();
        Dealer dealer = new Dealer();
        Deck deck = new Deck(1);

        // Даем игроку карты на сумму 5
        gambler.takeCard(Rank.TWO, Suit.HEARTS);
        gambler.takeCard(Rank.THREE, Suit.SPADES);

        // Передаем сканер с последовательностью: "1" (взять карту), затем "0" (остановиться)
        Scanner scanner = new Scanner("1\n0\n");

        Utils result = gambler.gamblerAction(gambler, dealer, scanner, deck);
        assertEquals(Utils.CONT, result);
    }

    @Test
    void testGamblerActionInvalidInputThenStop() {
        Gambler gambler = new Gambler();
        Dealer dealer = new Dealer();
        Deck deck = new Deck(1);

        // Даем игроку карты на сумму 10
        gambler.takeCard(Rank.FIVE, Suit.HEARTS);
        gambler.takeCard(Rank.FIVE, Suit.SPADES);

        // Передаем неверный ввод ("abc", "99"), а затем правильный ("0")
        Scanner scanner = new Scanner("abc\n99\n0\n");

        Utils result = gambler.gamblerAction(gambler, dealer, scanner, deck);
        assertEquals(Utils.CONT, result);
    }
}