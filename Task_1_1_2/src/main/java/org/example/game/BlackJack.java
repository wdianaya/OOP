package org.example.game;

import java.util.Scanner;

import org.example.cards.Card;
import org.example.cards.Deck;
import org.example.players.Dealer;
import org.example.players.Gambler;
import org.example.utils.ConsolePrinter;
import org.example.utils.GameResults;

/**
 * Класс, реализующий игровую логику блэкджека.
 */
public class BlackJack {
    private static final int INITIAL_HAND_SIZE = 2;
    private static final int MAX_SUM_CARDS = 21;
    /**
     * Реализовывает первую раздачу в игре.
     */
    private void initialDeal(Dealer dealer, Gambler gambler, Deck deck) {
        // раздача карт игроку
        for (int i = 0; i < INITIAL_HAND_SIZE; i++) {
            Card cardData = deck.giveCard();
            gambler.takeCard(cardData.rank(), cardData.suit());
        }
        // раздача карт дилеру
        Card dealerCard1 = deck.giveCard();
        dealer.takeCard(dealerCard1.rank(), dealerCard1.suit(), true);

        Card dealerCard2 = deck.giveCard();

        // оставляем одну карту закрытой
        dealer.takeCard(dealerCard2.rank(), dealerCard2.suit(), false);
    }

    private void handleRoundResult(GameResults result, Dealer dealer,
                                   Gambler gambler, Deck deck, ScoreBoard score) {
        if (result == GameResults.WIN) {
            score.incrementGamblerScore();
            ConsolePrinter.printBlackjackWin();
        } else if (result == GameResults.FAIL) {
            score.incrementDealerScore();
            ConsolePrinter.printBust();
        } else {
            GameResults dealerResult = dealer.dealerActions(dealer, gambler, deck);

            int gamblerSum = gambler.getFullSum();
            int dealerSum = dealer.getFullSum();

            if (dealerSum > MAX_SUM_CARDS || gamblerSum > dealerSum) {
                score.incrementGamblerScore();
                ConsolePrinter.printRoundWinner(true);
            } else if (gamblerSum < dealerSum) {
                score.incrementDealerScore();
                ConsolePrinter.printRoundWinner(false);
            } else {
                ConsolePrinter.printDraw();
            }
        }
    }

    private boolean askNextRound(Scanner scanner) {
        ConsolePrinter.printAskNextRoundPrompt();
        while (!scanner.hasNextInt()) {
            scanner.next();
            ConsolePrinter.printInputError();
        }

        int next = scanner.nextInt();
        scanner.nextLine();

        if (next == 0) {
            ConsolePrinter.printThankYou();
            return false; // игра прекращается
        }
        return true; // игра продолжается
    }

    /**
     * Запускает основной цикл игры.
     */
    public void mainPlay() {
        ConsolePrinter.printWelcome();

        Deck deck = new Deck(); // создание объекта колоды карт
        ScoreBoard score = new ScoreBoard(); // создание объекта для подсчёта очков

        int round = 0; // подсчёт раундов

        Scanner scanner = new Scanner(System.in); // для считывания ввода игрока

        GameResults result; // WIN | FAIL | CONT

        while (true) {
            ConsolePrinter.printRound(++round);

            Dealer dealer = new Dealer();
            Gambler gambler = new Gambler();

            initialDeal(dealer, gambler, deck);

            ConsolePrinter.printInitialDealMessage();

            ConsolePrinter.printLists(dealer, gambler); // выводим список текущих карт игрока и дилера

            // result содержит информацию о результате хода игрока
            result = gambler.gamblerAction(gambler, dealer, scanner, deck);

            handleRoundResult(result, dealer, gambler, deck, score); // обработка результатов хода игрока

            ConsolePrinter.printCurrentScore(score);

            if (!askNextRound(scanner)) {
                break;
            }
        }
        scanner.close();
    }
}
