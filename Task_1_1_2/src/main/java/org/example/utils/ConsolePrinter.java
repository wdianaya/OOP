package org.example.utils;

import java.util.List;

import org.example.cards.Card;
import org.example.cards.Rank;
import org.example.cards.Suit;
import org.example.game.ScoreBoard;
import org.example.players.Dealer;
import org.example.players.Gambler;

/**
 * Класс, для реализации методов вывода в консоль.
 */
public class ConsolePrinter {
    /**
     * Выводит текущий набор карт у игрока и дилера.
     */
    public static void printLists(Dealer dealer, Gambler gambler) {
        System.out.println("    Ваши карты: " + formatHand(gambler.getCards(), false)
                + " -> " + gambler.getFullSum());
        System.out.println("    Карты дилера: " + formatHand(dealer.getCards(), true)
                + " -> " + dealer.getVisibleSum());
    }

    public static void printWelcome() {
        System.out.println("Добро пожаловать в Блэкджек!");
    }

    public static void printRound(int round) {
        System.out.println("Раунд " + round);
    }

    public static void printInitialDealMessage() {
        System.out.println("Дилер раздал карты");
    }

    /**
     * Вывести текущий счёт по окончании раунда.
     */
    public static void printCurrentScore(ScoreBoard score) {
        System.out.println("Текущий счёт - Игрок: "
                + score.getGamblerScore() + " | Дилер: "
                + score.getDealerScore() + "\n");
    }

    public static void printBlackjackWin() {
        System.out.println("У вас блекджек! Вы выиграли раунд!");
    }

    public static void printBust() {
        System.out.println("У вас перебор! Вы проиграли раунд :(.");
    }

    /**
     * Вывести один из итогов раунда по результатам хода.
     */
    public static void printRoundWinner(boolean playerWon) {
        if (playerWon) {
            System.out.println("Вы выиграли раунд!");
        } else {
            System.out.println("Дилер выиграл раунд!");
        }
    }

    public static void printDraw() {
        System.out.println("Ничья в раунде!");
    }

    public static void printAskNextRoundPrompt() {
        System.out.println("Хотите сыграть еще раунд? (Введите 1 - продолжить, 0 - выйти):");
    }

    public static void printInputError() {
        System.out.println("Ошибка ввода. Введите 1 для продолжения или 0 для выхода:");
    }

    public static void printThankYou() {
        System.out.println("Спасибо за игру!");
    }

    public static void printDealerTurnHeader() {
        System.out.println("Ход Дилера\n--------");
    }

    /**
     * Вывести информацию о том что дилер открыл закрытую карту и эту карту.
     */
    public static void printDealerRevealedHiddenCard(Rank rank, Suit suit) {
        System.out.println("Дилер открывает закрытую карту "
                + rank.getName()
                + " " + suit.getSymbol() + " ("
                + rank.getScore() + ")");
    }

    /**
     * Вывод информации о последующих откртиях карты дилером.
     */
    public static void printDealerDrewCard(Rank rank, Suit suit) {
        System.out.println("Дилер открывает карту "
                + rank.getName()
                + " " + suit.getSymbol()
                + " (" + rank.getScore() + ")");
    }

    /**
     * Формирует строковое представление текущего набора карт игрока.
     * onlyOpen=true выводит только открытые карты
     * onlyOpen=false выводит все карты
     */
    public static String formatHand(List<Card> cards, boolean onlyOpen) {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            Suit suit = card.suit();
            Rank rank = card.rank();
            boolean isOpen = card.isOpen();

            if (onlyOpen && !isOpen) {
                str.append("<закрытая карта>");
            } else {
                str.append(rank.getName())
                        .append(" ")
                        .append(suit.getSymbol())
                        .append(" (")
                        .append(rank.getScore())
                        .append(")");
            }
            if (i < cards.size() - 1) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }

    public static void printGamblerTurnHeader() {
        System.out.println("Ваш ход\n--------");
    }

    public static void printGamblerPrompt() {
        System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться ...\n");
    }

    public static void printGamblerInputError() {
        System.out.println("Ошибка ввода. Введите “1”,"
                + " чтобы взять карту, и “0”, чтобы остановиться ...");
    }

    /**
     * Вывести информацию о открытой карте игрока.
     */
    public static void printGamblerDrewCard(Rank rank, Suit suit) {
        System.out.println("Вы открыли карту "
                + rank.getName()
                + " " + suit.getSymbol()
                + " (" + rank.getScore()
                + ")");
    }

    public static void printInvalidNumber() {
        System.out.println("Введено некорректное число.");
    }

    /**
     * Вывести текущую сумму очково с карт.
     */
    public static void printScore(int score) {
        System.out.println(score);
    }
}
