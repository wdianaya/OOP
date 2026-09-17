package org.example.players;

import java.util.Scanner;

import org.example.cards.Card;
import org.example.cards.Deck;
import org.example.cards.Rank;
import org.example.cards.Suit;
import org.example.utils.ConsolePrinter;
import org.example.utils.GameResults;

/**
 * Класс, для создания объекта игрока.
 */
public class Gambler extends Player {
    /**
     * Реализация хода игрока.
     */
    public GameResults gamblerAction(Gambler gambler, Dealer dealer, Scanner scanner, Deck deck) {
        int num; // num - ввод пользователя (1 или 0)
        int score = gambler.getFullSum(); // перерасчитываем общую сумму очков
        // оценка текущей суммы очков относительно 21
        GameResults curRes = GameResults.checkedRes(score);

        ConsolePrinter.printGamblerTurnHeader();

        while (curRes == GameResults.CONT) {
            ConsolePrinter.printGamblerPrompt();

            // если игрок вводит не число - заходим в цикл
            while (!scanner.hasNextInt()) {
                scanner.next();
                ConsolePrinter.printGamblerInputError();
            }

            // считываем ввод
            num = scanner.nextInt();
            switch (num) {
                case 0:
                    // при выходе перерасчитываем итоговую сумму
                    score = gambler.getFullSum();
                    return GameResults.checkedRes(score);
                case 1:
                    // реализация взятия карты и вывода сообщения о ней
                    Card cardData = deck.giveCard();
                    Suit suit = cardData.suit();
                    Rank rank = cardData.rank();
                    gambler.takeCard(rank, suit);
                    ConsolePrinter.printGamblerDrewCard(rank, suit);
                    break;
                default:
                    // если было введено число отличное от 0 и 1
                    ConsolePrinter.printInvalidNumber();
                    continue;
            }

            // обновляем итоговую сумму, выводим состояние карт игрока и дилера
            score = gambler.getFullSum();
            ConsolePrinter.printLists(dealer, gambler);

            // проверка на блекджек и проигрыш
            curRes = GameResults.checkedRes(score);
            ConsolePrinter.printScore(score);
        }
        return curRes;
    }
}
