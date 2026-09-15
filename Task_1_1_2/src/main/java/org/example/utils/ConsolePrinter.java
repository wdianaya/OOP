package org.example.utils;

import org.example.players.Dealer;
import org.example.players.Gambler;

/**
 * Класс, для реализации методов вывода в консоль.
 */
public class ConsolePrinter {
    /**
     * Выводит текущий набор карт у игрока и дилера.
     */
    public static void printLists(Dealer dealer, Gambler gambler) {
        System.out.println("    Ваши карты: " + gambler.printCard(false)
                + " -> " + gambler.getFullSum());
        System.out.println("    Карты дилера: " + dealer.printCard(true)
                + " -> " + dealer.getVisibleSum());
    }
}
