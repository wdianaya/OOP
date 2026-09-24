package org.example.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.example.cards.Rank;
import org.example.cards.Suit;
import org.example.players.Dealer;
import org.example.players.Gambler;
import org.junit.jupiter.api.Test;

class ConsolePrinterTest {
    @Test
    void testPrintLists() {
        // 1. Создаем поток для перехвата консольного вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // 2. Создаем игроков и выдаем им конкретные карты для предсказуемого результата
            Gambler gambler = new Gambler();
            gambler.takeCard(Rank.SEVEN, Suit.HEARTS);
            gambler.takeCard(Rank.ACE, Suit.SPADES);

            Dealer dealer = new Dealer();
            dealer.takeCard(Rank.TEN, Suit.DIAMONDS, true); // видимая карта
            dealer.takeCard(Rank.FIVE, Suit.CLUBS, false);  // закрытая карта

            // 3. Вызываем тестируемый метод
            ConsolePrinter.printLists(dealer, gambler);

            // 4. Получаем то, что напечаталось в консоль
            String printedOutput = outputStream.toString();

            // 5. Проверяем, что в выводе содержатся ключевые данные (карты и суммы)
            assertTrue(printedOutput.contains("Ваши карты:"));
            assertTrue(printedOutput.contains("Карты дилера:"));
            // Сумма 7 (семерка) + 11 (туз) = 18
            assertTrue(printedOutput.contains("18"));
            // Видимая сумма дилера (только карта 10) = 10
            assertTrue(printedOutput.contains("10"));

        } finally {
            // 6. Обязательно возвращаем стандартный вывод на место,
            // чтобы не сломать другие тесты
            System.setOut(originalOut);
        }
    }
}