package org.example.players;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

class BlackJackTest {

    @Test
    void testMainPlayExecution() {
        // Имитируем ввод пользователя:
        // Первый "0" — завершить ход игрока (остановиться)
        // Второй "0" — выйти из игры после окончания раунда
        String simulatedInput = "0\n0\n";
        InputStream savedStdin = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            // Создаем экземпляр игры и запускаем main_play
            BlackJack game = new BlackJack();

            // Проверяем, что метод отрабатывает без исключений и зависаний
            assertDoesNotThrow(game::main_play);
        } finally {
            // Обязательно возвращаем стандартный ввод обратно
            System.setIn(savedStdin);
        }
    }
}