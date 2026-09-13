package org.example;

import java.util.Random;
import java.util.Scanner;

public class BlackJack {
    private int round;
    private Deck deck;

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
            gambler.takeCard((Rank)cardData[1], (Suit)cardData[0]);
        }

        // раздача карт дилеру
        Object[] dealerCard1 = deck.giveCard();
        dealer.takeCard((Rank)dealerCard1[1], (Suit)dealerCard1[0], true);

        Object[] dealerCard2 = deck.giveCard();
        dealer.takeCard((Rank)dealerCard2[1], (Suit)dealerCard2[0], false);
    }

    public void main_play() {
        getStart();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ++round;
            System.out.println("Раунд " + round);

            Dealer dealer = new Dealer();
            Gambler gambler = new Gambler();

            initialDeal(dealer, gambler);

            System.out.println("Дилер раздал карты");

            System.out.println("    Ваши карты: " + gambler.printCard(false) + " -> " + gambler.getFullSum());
            System.out.println("    Карты дилера: " + dealer.printCard(true));

            break;
        }
    }
}
