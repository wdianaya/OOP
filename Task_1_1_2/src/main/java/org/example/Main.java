package org.example;

import org.example.players.BlackJack;

/**
 * Главный класс для запуска программы.
 */
public class Main {
    /**
     * Вызывает метод, реализующий основной алгоритм игры.
     */
    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.main_play();
    }
}