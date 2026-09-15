package org.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void testMainExecution() {
        // Первый "0" — завершить ход игрока, второй "0" — выйти из цикла раундов
        String simulatedInput = "0\n0\n";
        InputStream savedStdin = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            assertDoesNotThrow(() -> {
                Main.main(new String[]{});
            });
        } finally {
            System.setIn(savedStdin);
        }
    }
}