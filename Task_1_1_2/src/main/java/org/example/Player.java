package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected List<Object[]> myCards = new ArrayList<>(); // [Rank, Suit, isOpen] коллекция для хранения карт игроков

    // взятие карты для дилера
    public void takeCard(Rank rank, Suit suit, boolean isOpen) {
        myCards.add(new Object[] {suit, rank, isOpen});
    }

    // Взятие карты для игрока
    public void takeCard(Rank rank, Suit suit) {
        takeCard(rank, suit, true);
    }

    // расчёт очков с текущих карт в коллекции
    // onlyOpen=true считает только открытые карты (нужно для показа очков дилера игроку)
    // onlyOpen=false считает абсолютно все карты
    public int calculateScore(boolean onlyOpen) {
        int sum = 0; // общая сумма значений всех карт колоды
        int acesCount = 0; // счётчик тузов в текущей колоде

        for (Object[] cardData : myCards) {
            Rank rank = (Rank)cardData[1];
            boolean isOpen = (boolean)cardData[2];

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
        // если в колоде присутствовали тузы и значение привысило 21 - делаем вклад тузов равный 1 в общую сумму
        while (sum > 21 && acesCount > 0) {
            sum -= 10;
            acesCount--;
        }
        return sum;
    }

    // вывести список текущих карт
    // onlyOpen=true выводит только открытые карты
    // onlyOpen=false выводит все карты
    public String printCard(boolean onlyOpen) {
        StringBuilder str = new StringBuilder("[");
        int count=0;
        for (Object[] cardData : myCards) {
            Suit suit = (Suit)cardData[0];
            Rank rank = (Rank)cardData[1];
            boolean isOpen = (boolean)cardData[2];

            // выводим только открытые и сама карта закрыта (isOpen=false)
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
            if (count < myCards.size()-1) {
                str.append(", ");
            }
            count++;
        }
        str.append("]");
        return str.toString();
    }

    // получение суммы значений для всех карт
    public int getFullSum() {
        return calculateScore(false);
    }

    // получение суммы только открытых карт (для дилера во время хода игрока)
    public int getVisibleSum() {
        return calculateScore(true);
    }
}