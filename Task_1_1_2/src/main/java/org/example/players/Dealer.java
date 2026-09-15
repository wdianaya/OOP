package org.example.players;

import java.util.List;

import org.example.cards.Deck;
import org.example.cards.Rank;
import org.example.cards.Suit;
import org.example.utils.ConsolePrinter;
import org.example.utils.Utils;

/**
 * Класс, реализовывающий логику дилера.
 */
public class Dealer extends Player {
    /**
     * Проверяет,  что текущая сумма очков с карт меньше 17.
     */
    public boolean shouldTakeCard() {
        return getFullSum() < 17;
    }

    /**
     * Открывает закрытую карту и возвращает её.
     */
    public List<Object> takeOpen() {
        for (Object[] cardData : myCards) {
            boolean isOpen = (boolean) cardData[2];
            Rank rank = (Rank) cardData[1];
            Suit suit = (Suit) cardData[0];
            if (!isOpen) {
                cardData[2] = true;
                return List.of(rank, suit, true);
            }
        }
        // возвращаем пустой список, если не было закрытой карты
        return List.of();
    }

    /**
     * Добавляет карту дилеру с возможностью указать статус её видимости.
     * Переопределяет базовый метод класса Player для корректной раздачи открытых и закрытых карт.
     */
    @Override
    public void takeCard(Rank rank, Suit suit, boolean isOpen) {
        super.takeCard(rank, suit, isOpen);
    }

    /**
     * Реализация хода дилера.
     */
    public Utils dealerActions(Dealer dealer, Gambler gambler, Deck deck) {
        System.out.println("Ход Дилера\n--------");
        // открываем закрытую карту
        List<Object> openCard = dealer.takeOpen();

        if (!openCard.isEmpty()) {
            Rank rank0 = (Rank) openCard.get(0);
            Suit suit0 = (Suit) openCard.get(1);
            System.out.println("Дилер открывает закрытую карту "
                    + rank0.getName()
                    + " " + suit0.getSymbol() + " ("
                    + rank0.getScore() + ")");
            // делаем перерасчёт общий суммы с учетом открытой карты
            int score = dealer.getVisibleSum();
            Utils.checkedRes(score);

            ConsolePrinter.printLists(dealer, gambler);
        }
        int score = dealer.getVisibleSum();
        Utils curRes = Utils.checkedRes(score);

        // дилер берет карты пока сумма не привысит 17
        while (dealer.shouldTakeCard()) {
            Object[] cardData = deck.giveCard();
            Suit suit = (Suit) cardData[0];
            Rank rank = (Rank) cardData[1];

            System.out.println("Дилер открывает карту "
                    + rank.getName()
                    + " " + suit.getSymbol()
                    + " (" + rank.getScore() + ")");
            dealer.takeCard(rank, suit, true);
            score = dealer.getFullSum();
            curRes = Utils.checkedRes(score);
            ConsolePrinter.printLists(dealer, gambler);
        }
        return curRes;
    }
}
