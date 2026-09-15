package org.example.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Класс, представляющий колоду игральных карт.
 */
public class Deck {
    private final List<Object[]> cards = new ArrayList<>();

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
     * @param numberOfDecks количество стандартных колод, объединяемых в одну.
     */
    public void initializeDeck(int numberOfDecks) {
        for (int i = 0; i < numberOfDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Object[]{suit, rank});
                }
            }
        }
        shuffle();
    }

    /**
     * Перемещивает карты в колоде.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Выдает верхнюю карту из колоды и удаляет её из неё.
     *
     * @return массив объектов, содержащий масть и достоинство карты
     * @throws IllegalStateException если колода опустела
     */
    public Object[] giveCard() {
        if (cards.isEmpty()) { // если на каком-то из раундов закончатся карты
            throw new IllegalStateException("Колода пуста!");
        }
        return cards.remove(cards.size() - 1);
    }
}
