package org.example.players;

import java.util.Scanner;

import org.example.cards.Deck;
import org.example.cards.Rank;
import org.example.cards.Suit;
import org.example.utils.ConsolePrinter;
import org.example.utils.Utils;

/**
 * Класс, реализующий игровую логику блэкджека.
 */
public class BlackJack {
    /**
     * Реализовывает первую раздачу в игре.
     */
    private void initialDeal(Dealer dealer, Gambler gambler, Deck deck) {
        // раздача карт игроку
        for (int i = 0; i < 2; i++) {
            Object[] cardData = deck.giveCard();
            gambler.takeCard((Rank) cardData[1], (Suit) cardData[0]);
        }

        // раздача карт дилеру
        Object[] dealerCard1 = deck.giveCard();
        dealer.takeCard((Rank) dealerCard1[1], (Suit) dealerCard1[0], true);

        Object[] dealerCard2 = deck.giveCard();
        // оставляем одну карту закрытой
        dealer.takeCard((Rank) dealerCard2[1], (Suit) dealerCard2[0], false);
    }

    /**
     * Запускает основной цикл игры.
     */
    public void main_play() {
        System.out.println("Добро пожаловать в Блэкджек!");

        Deck deck = new Deck(); // создание объекта колоды карт

        int round = 0; // подсчёт раундов

        int gamblerScore = 0;
        int dealerScore = 0;

        // объект класса Scanner для считывания ввода игрока
        Scanner scanner = new Scanner(System.in);

        Utils result; // WIN | FAIL | CONT

        while (true) {
            ++round;
            System.out.println("Раунд " + round);

            // создаем игрока и дилера
            Dealer dealer = new Dealer();
            Gambler gambler = new Gambler();

            // реализуем первую раздачу карт
            initialDeal(dealer, gambler, deck);

            System.out.println("Дилер раздал карты");

            // выводим список текущих карт игрока и дилера
            ConsolePrinter.printLists(dealer, gambler);

            // result содержит информацию о результате хода игрока
            result = gambler.gamblerAction(gambler, dealer, scanner, deck);

            // обработка результатов хода игрока
            if (result == Utils.WIN) {
                gamblerScore++;
                System.out.println("У вас блекджек! Вы выиграли раунд!");
            } else if (result == Utils.FAIL) {
                dealerScore++;
                System.out.println("У вас перебор! Вы проиграли раунд :(.");
            } else {
                Utils dealerResult = dealer.dealerActions(dealer, gambler, deck);

                int gamblerSum = gambler.getFullSum();
                int dealerSum = dealer.getFullSum();

                // сравнение очков (если у дилера перебор > 21 или у игрока больше)
                if (dealerSum > 21 || gamblerSum > dealerSum) {
                    gamblerScore++;
                    System.out.println("Вы выиграли раунд!");
                } else if (gamblerSum < dealerSum) {
                    dealerScore++;
                    System.out.println("Дилер выиграл раунд!");
                } else {
                    System.out.println("Ничья в раунде!");
                }
            }

            System.out.println("Текущий счёт - Игрок: "
                    + gamblerScore + " | Дилер: " + dealerScore + "\n");

            // запрос на следующий раунд
            System.out.println("Хотите сыграть еще раунд? (Введите 1 - продолжить, 0 - выйти):");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Ошибка ввода. Введите 1 для продолжения или 0 для выхода:");
            }
            int next = scanner.nextInt();
            if (next == 0) {
                System.out.println("Спасибо за игру!");
                break;
            }
        }
    }
}
