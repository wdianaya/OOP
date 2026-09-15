package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Scanner;

import org.example.cards.Rank;
import org.example.cards.Suit;
import org.example.players.BlackJack;
import org.example.players.Dealer;
import org.example.players.Gambler;
import org.example.utils.Utils;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    @Test
    void testCheckedRes() {
        BlackJack game = new BlackJack();
        assertEquals(Utils.WIN, game.checkedRes(21));
        assertEquals(Utils.FAIL, game.checkedRes(22));
        assertEquals(Utils.FAIL, game.checkedRes(25));
        assertEquals(Utils.CONT, game.checkedRes(20));
        assertEquals(Utils.CONT, game.checkedRes(0));
    }

    @Test
    void testGetStart() {
        BlackJack game = new BlackJack();
        game.getStart();
        assertNotNull(game);
    }

    @Test
    void testGamblerActionStopImmediately() {
        BlackJack game = new BlackJack();
        game.getStart();

        Gambler gambler = new Gambler();
        Dealer dealer = new Dealer();

        // Даем игроку карты на сумму 9 (состояние CONT)
        gambler.takeCard(Rank.SEVEN, Suit.HEARTS);
        gambler.takeCard(Rank.TWO, Suit.SPADES);

        // Передаем сканер с вводом "0" (остановиться)
        Scanner scanner = new Scanner("0\n");

        Utils result = game.gamblerAction(gambler, dealer, scanner);
        assertEquals(Utils.CONT, result);
    }

    @Test
    void testGamblerActionTakeCardAndStop() {
        BlackJack game = new BlackJack();
        game.getStart();

        Gambler gambler = new Gambler();
        Dealer dealer = new Dealer();

        // Даем игроку карты на сумму 5
        gambler.takeCard(Rank.TWO, Suit.HEARTS);
        gambler.takeCard(Rank.THREE, Suit.SPADES);

        // Передаем сканер с последовательностью: "1"
        // (взять карту), затем "0" (остановиться)
        Scanner scanner = new Scanner("1\n0\n");

        Utils result = game.gamblerAction(gambler, dealer, scanner);
        assertEquals(Utils.CONT, result);
    }
}