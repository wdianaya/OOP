package org.example.players;

import java.util.List;
import org.example.cards.Card;
import org.example.cards.Deck;
import org.example.cards.Rank;
import org.example.cards.Suit;
import org.example.utils.ConsolePrinter;
import org.example.utils.GameResults;

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
    public List<Card> takeOpen() {
        for (Card card : myCards) {
            if (!card.isOpen()) {
                Card openedCard = new Card(card.suit(), card.rank(), true);
                int index = myCards.indexOf(card);
                myCards.set(index, openedCard);
                return List.of(openedCard);
            }
        }
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
    public GameResults dealerActions(Dealer dealer, Gambler gambler, Deck deck) {
        ConsolePrinter.printDealerTurnHeader();

        // открываем закрытую карту
        List<Card> openCards = dealer.takeOpen();

        if (!openCards.isEmpty()) {
            Card card = openCards.get(0);
            ConsolePrinter.printDealerRevealedHiddenCard(card.rank(), card.suit());

            // делаем перерасчёт общей суммы с учетом открытой карты
            int score = dealer.getVisibleSum();
            GameResults.checkedRes(score);

            ConsolePrinter.printLists(dealer, gambler);
        }

        int score = dealer.getVisibleSum();
        GameResults curRes = GameResults.checkedRes(score);

        // дилер берет карты, пока должен брать по правилам
        while (dealer.shouldTakeCard()) {
            Card cardData = deck.giveCard();
            ConsolePrinter.printDealerDrewCard(cardData.rank(), cardData.suit());

            dealer.takeCard(cardData.rank(), cardData.suit(), true);
            score = dealer.getFullSum();
            curRes = GameResults.checkedRes(score);
            ConsolePrinter.printLists(dealer, gambler);
        }
        return curRes;
    }
}
