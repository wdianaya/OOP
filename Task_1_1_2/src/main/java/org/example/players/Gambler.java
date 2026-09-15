package org.example.players;

import java.util.Scanner;

import org.example.cards.Deck;
import org.example.cards.Rank;
import org.example.cards.Suit;
import org.example.utils.ConsolePrinter;
import org.example.utils.Utils;

/**
 * Класс, для создания объекта игрока.
 */
public class Gambler extends Player {
    /**
     * Реализация хода игрока.
     */
    public Utils gamblerAction(Gambler gambler, Dealer dealer, Scanner scanner, Deck deck) {
        int num; // num - ввод пользователя (1 или 0)
        int score; // score - сумма очков текущих карт в коллекции
        score = gambler.getFullSum(); // перерасчитываем общую сумму очков
        Utils curRes = Utils.checkedRes(score); // оценка текущей суммы очков относительно 21
        System.out.println("Ваш ход\n--------");
        while (curRes == Utils.CONT) {

            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться ...\n");
            // если игрок вводит не число - заходим в цикл
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Ошибка ввода. Введите “1”,"
                        + " чтобы взять карту, и “0”, чтобы остановиться ...");
            }
            // считываем ввод
            num = scanner.nextInt();
            switch (num) {
                case 0:
                    // при выходе перерасчитываем итоговую сумму
                    score = gambler.getFullSum();
                    curRes = Utils.checkedRes(score);
                    return curRes;
                case 1:
                    // реализация взятия карты и вывода сообщения о ней
                    Object[] cardData = deck.giveCard();
                    Suit suit = (Suit) cardData[0];
                    Rank rank = (Rank) cardData[1];
                    gambler.takeCard(rank, suit);
                    System.out.println("Вы открыли карту "
                            + rank.getName()
                            + " " + suit.getSymbol()
                            + " (" + rank.getScore()
                            + ")");
                    gambler.getFullSum();
                    break;
                default:
                    // если было введено число отличное от 0 и 1
                    System.out.println("Введено некорректное число.");
                    continue;
            }
            // обновляем итоговую сумму, выводим состояние карт игрока и дилера
            score = gambler.getFullSum();
            ConsolePrinter.printLists(dealer, gambler);

            // проверка на блекджек и проигрыш
            curRes = Utils.checkedRes(score);
            System.out.println(score);
        }
        return curRes;
    }
}
