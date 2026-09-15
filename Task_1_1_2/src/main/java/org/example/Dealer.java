package org.example;

import java.util.List;

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
     * Добавляет карту дилеру с возможностью указать статус её видимости
     * Переопределяет базовый метод класса Player для корректной раздачи открытых и закрытых карт
     */
    @Override
    public void takeCard(Rank rank, Suit suit, boolean isOpen) {
        super.takeCard(rank, suit, isOpen);
    }

}
