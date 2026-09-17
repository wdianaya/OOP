package org.example.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Класс, представляющий колоду игральных карт.
 */
public class Deck {
    private final List<Card> cards = new ArrayList<>();

    /**
     * Конструктор по умолчанию: генерирует случайное количество колод (от 1 до 3).
     */
    public Deck() {
        Random random = new Random();
        int count = random.nextInt(3) + 1;
        initializeDeck(count);
    }

    /**
     * Конструктор с параметром (нужен для тестов).
     */
    public Deck(int count) {
        initializeDeck(count);
    }

    /**
     * Создает колоду карт указанного размера (количество стандартных колод по 52 карты).
     *
     * @param countOfDecks количество стандартных колод, объединяемых в одну.
     */
    private void initializeDeck(int countOfDecks) {
        for (int i = 0; i < countOfDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(suit, rank, true));
                }
            }
        }
        shuffleCards();
    }

    /**
     * Перемещивает карты в колоде.
     */
    private void shuffleCards() {
        Collections.shuffle(cards);
    }

    private void addCardInDeck(Card card) {
        cards.add(card);
    }

    /**
     * Выдает верхнюю карту из колоды и удаляет её из неё.
     *
     * @return массив объектов, содержащий масть и достоинство карты
     * @throws IllegalStateException если колода опустела
     */
    public Card giveCard() {
        if (cards.isEmpty()) { // если на каком-то из раундов закончатся карты
            throw new IllegalStateException("Колода пуста!");
        }
        return cards.remove(cards.size() - 1);
    }
}
