package org.example;

import jdk.jshell.execution.Util;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {
    private int round; // подсчёт раундов
    private Deck deck; // созданиеи объекта колоды карт
    Scanner scanner = new Scanner(System.in); // объект класса Scanner для считывания ввода игрока

    // приветственное окно
    public void getStart() {
        System.out.println("Добро пожаловать в Блэкджек!");

        // рандомно определяем количество колод в игре
        Random random = new Random();
        int count = random.nextInt(3) + 1;
        this.deck = new Deck(count);
    }

    // реализация первой раздачи
    private void initialDeal(Dealer dealer, Gambler gambler) {
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

    // промежуточная проверка общей суммы очков
    public Utils checkedRes(int score) {
        if (score == 21) {
            return Utils.WIN;
        }
        if (score > 21) {
            return Utils.FAIL;
        }
        return Utils.CONT;
    }

    // ход игрока
    public Utils gamblerAction(Gambler gambler, Dealer dealer) {
        int num, score; // num - ввод пользователя (1 или 0), score - сумма очков текущих карт в коллекции
        score = gambler.getFullSum(); // перерасчитываем общую сумму очков
        Utils curRes = checkedRes(score); // оценка текущей суммы очков относительно 21
        System.out.println("Ваш ход\n--------");
        while (curRes == Utils.CONT) {

            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться ...\n");
            // если игрок вводит не число - заходим в цикл
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Ошибка ввода. Введите “1”, чтобы взять карту, и “0”, чтобы остановиться ...");
            }
            // считываем ввод
            num = scanner.nextInt();
            switch (num) {
                case 0:
                    // при выходе перерасчитываем итоговую сумму
                    score = gambler.getFullSum();
                    curRes = checkedRes(score);
                    return curRes;
                case 1:
                    // реализация взятия карты и вывода сообщения о ней
                    Object[] cardData = deck.giveCard();
                    Suit suit = (Suit) cardData[0];
                    Rank rank = (Rank) cardData[1];
                    gambler.takeCard(rank, suit);
                    System.out.println("Вы открыли карту " +
                            rank.getName() +
                            " " + suit.getSymbol() +
                            " (" + rank.getScore() + ")");
                    score = gambler.getFullSum();
                    break;
                default:
                    // если было введено число отличное от 0 и 1
                    System.out.println("Введено некорректное число.");
                    continue;
            }
            // обновляем итоговую сумму, выводим состояние карт игрока и дилера
            score = gambler.getFullSum();
            printLists(dealer, gambler);

            // проверка на блекджек и проигрыш
            curRes = checkedRes(score);
            System.out.println(score);
        }
        return curRes;
    }

    // ход дилера
    public Utils dealerActions(Dealer dealer, Gambler gambler) {
        System.out.println("Ход Дилера\n--------");
        // открываем закрытую карту
        List<Object> openCard = dealer.takeOpen();

        if (!openCard.isEmpty()) {
            Rank rank0 = (Rank) openCard.get(0);
            Suit suit0 = (Suit) openCard.get(1);
            System.out.println("Дилер открывает закрытую карту " +
                    rank0.getName() +
                    " " + suit0.getSymbol() + " (" +
                    rank0.getScore() + ")");
            // делаем перерасчёт общий суммы с учетом открытой карты
            int score = dealer.getVisibleSum();
            Utils curRes = checkedRes(score);

            printLists(dealer, gambler);
        }
        int score = dealer.getVisibleSum();
        Utils curRes = checkedRes(score);

        // дилер берет карты пока сумма не привысит 17
        while (dealer.shouldTakeCard()) {
            Object[] cardData = deck.giveCard();
            Suit suit = (Suit) cardData[0];
            Rank rank = (Rank) cardData[1];

            System.out.println("Дилер открывает карту " +
                    rank.getName() +
                    " " + suit.getSymbol() +
                    " (" + rank.getScore() + ")");
            dealer.takeCard(rank, suit, true);
            score = dealer.getFullSum();
            curRes = checkedRes(score);
            printLists(dealer, gambler);
        }
        return curRes;
    }

    // вывести текущий набор карт
    public void printLists(Dealer dealer, Gambler gambler) {
        System.out.println("    Ваши карты: " + gambler.printCard(false) + " -> " + gambler.getFullSum());
        System.out.println("    Карты дилера: " + dealer.printCard(true) + " -> " + dealer.getVisibleSum());
    }

    // основной алгоритм игры
    public void main_play() {
        getStart();
        int gambler_score = 0;
        int dealer_score = 0;
        Utils result; // WIN | FAIL | CONT

        while (true) {
            ++round;
            System.out.println("Раунд " + round);

            // создаем игрока и дилера
            Dealer dealer = new Dealer();
            Gambler gambler = new Gambler();

            // реализуем первую раздачу карт
            initialDeal(dealer, gambler);

            System.out.println("Дилер раздал карты");

            // выводим список текущих карт игрока и дилера
            printLists(dealer, gambler);

            // result содержит информацию о результате хода игрока
            result = gamblerAction(gambler, dealer);

            // обработка результатов хода игрока
            if (result == Utils.WIN) {
                gambler_score++;
                System.out.println("У вас блекджек! Вы выиграли раунд!");
            } else if (result == Utils.FAIL) {
                dealer_score++;
                System.out.println("У вас перебор! Вы проиграли раунд :(.");
            } else {
                Utils dealerResult = dealerActions(dealer, gambler);

                int gamblerSum = gambler.getFullSum();
                int dealerSum = dealer.getFullSum();

                // сравнение очков (если у дилера перебор > 21 или у игрока больше)
                if (dealerSum > 21 || gamblerSum > dealerSum) {
                    gambler_score++;
                    System.out.println("Вы выиграли раунд!");
                } else if (gamblerSum < dealerSum) {
                    dealer_score++;
                    System.out.println("Дилер выиграл раунд!");
                } else {
                    System.out.println("Ничья в раунде!");
                }
            }

            System.out.println("Текущий счёт - Игрок: " + gambler_score + " | Дилер: " + dealer_score + "\n");

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
