package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public int count;
    private List<Object[]> cards = new ArrayList<>();

    // создание коллекции с картами, numberOfDecks - количсетво колод
    public Deck(int numberOfDecks) {
        for (int i = 0; i < numberOfDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Object[] {suit, rank});
                }
            }
        }
        shuffle();
    }

    // перемешать карты в колоде
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // взять одну карту
    public Object[] giveCard() {
        if (cards.isEmpty()) { // если на каком-то из раундов закончатся карты
            throw new IllegalStateException("Колода пуста!");
        }
        return cards.remove(cards.size() - 1);
    }
}
