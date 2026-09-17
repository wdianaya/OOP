package org.example.players;

import java.util.ArrayList;
import java.util.List;

import org.example.cards.Card;
import org.example.cards.Rank;
import org.example.cards.Suit;

/**
 * Абстрактный класс, представляющий базового игрока в блэкджек.
 * Содержит общую логику хранения карт, подсчета очков и вывода информации.
 */
public abstract class Player {
    private static final int MAX_SUM_CARDS = 21;
    /**
     * Коллекция для хранения карт игрока.
     */
    protected List<Card> myCards = new ArrayList<>();

    /**
     * Добавляет карту в руку игрока с указанием статуса её видимости.
     *
     * @param rank   достоинство карты
     * @param suit   масть карты
     * @param isOpen статус видимости (true — открыта, false — закрыта)
     */
    public void takeCard(Rank rank, Suit suit, boolean isOpen) {
        myCards.add(new Card(suit, rank, isOpen));
    }

    /**
     * Добавляет открытую карту в руку игрока (по умолчанию isOpen = true).
     *
     * @param rank достоинство карты
     * @param suit масть карты
     */
    public void takeCard(Rank rank, Suit suit) {
        takeCard(rank, suit, true);
    }

    /**
     * Вычисляет общую сумму очков на руках с учетом правил для тузов.
     * onlyOpen=true считает только открытые карты (нужно для показа очков дилера игроку)
     * onlyOpen=false считает абсолютно все карты
     */
    public int calculateScore(boolean onlyOpen) {
        int sum = 0; // общая сумма значений всех карт
        int acesCount = 0; // счётчик тузов

        for (Card card : myCards) {
            Rank rank = card.rank();
            boolean isOpen = card.isOpen();

            // пропускаем закрытые карты, если смотрим открытые
            if (onlyOpen && !isOpen) {
                continue;
            }
            sum += rank.getScore();
            // увеличиваем количество тузов
            if (rank == Rank.ACE) {
                acesCount++;
            }
        }
        // если в колоде присутствовали тузы и значение превысило 21,
        // делаем вклад тузов равным 1 в общую сумму
        while (sum > MAX_SUM_CARDS && acesCount > 0) {
            sum -= Rank.ACE.getScore();
            acesCount--;
        }
        return sum;
    }

    /**
     * Возвращает сумму значений всех карт (открытых и закрытых).
     */
    public int getFullSum() {
        return calculateScore(false);
    }

    /**
     * Возвращает сумму только открытых карт (используется для дилера во время хода игрока).
     */
    public int getVisibleSum() {
        return calculateScore(true);
    }

    public List<Card> getCards() {
        return myCards;
    }
}