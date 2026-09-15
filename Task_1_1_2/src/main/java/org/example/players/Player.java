package org.example.players;

import org.example.cards.Rank;
import org.example.cards.Suit;

import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактный класс, представляющий базового игрока в блэкджек.
 * Содержит общую логику хранения карт, подсчета очков и вывода информации.
 */
public abstract class Player {
    /**
     * Коллекция для хранения карт игрока в формате [Suit, Rank, isOpen].
     */
    protected List<Object[]> myCards = new ArrayList<>();

    /**
     * Добавляет карту в руку игрока с указанием статуса её видимости.
     *
     * @param rank   достоинство карты
     * @param suit   масть карты
     * @param isOpen статус видимости (true — открыта, false — закрыта)
     */
    public void takeCard(Rank rank, Suit suit, boolean isOpen) {
        myCards.add(new Object[]{suit, rank, isOpen});
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
        int sum = 0; // общая сумма значений всех карт колоды
        int acesCount = 0; // счётчик тузов в текущей колоде

        for (Object[] cardData : myCards) {
            Rank rank = (Rank) cardData[1];
            boolean isOpen = (boolean) cardData[2];

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
        // если в колоде присутствовали тузы и значение привысило 21,
        // делаем вклад тузов равный 1 в общую сумму
        while (sum > 21 && acesCount > 0) {
            sum -= 10;
            acesCount--;
        }
        return sum;
    }

    /**
     * Формирует строковое представление текущего набора карт игрока.
     * onlyOpen=true выводит только открытые карты
     * onlyOpen=false выводит все карты
     */
    public String printCard(boolean onlyOpen) {
        StringBuilder str = new StringBuilder("[");
        int count = 0;
        for (Object[] cardData : myCards) {
            Suit suit = (Suit) cardData[0];
            Rank rank = (Rank) cardData[1];
            boolean isOpen = (boolean) cardData[2];

            // если выводим только открытые и сама карта закрыта (isOpen=false)
            if (onlyOpen && !isOpen) {
                str.append("<закрытая карта>");
            } else {
                str.append(rank.getName());
                str.append(" ");
                str.append(suit.getSymbol());
                str.append(" (");
                str.append(rank.getScore());
                str.append(")");
            }
            if (count < myCards.size() - 1) {
                str.append(", ");
            }
            count++;
        }
        str.append("]");
        return str.toString();
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
}