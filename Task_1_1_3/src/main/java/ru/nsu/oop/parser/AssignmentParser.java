package ru.nsu.oop.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, реализующий разбор строки означивания
 * вида: "x = 10; y = 13".
 */
public final class AssignmentParser {
    /**
     * Возвращает объекты вида переменная - значение.
     */
    public static Map<String, Integer> parse(String input) {
        Map<String, Integer> result = new HashMap<>();

        if (input == null || input.isBlank()) {
            return result;
        }

        for (String part : input.split(";")) {
            String trimmed = part.trim();
            String[] pair = part.split("=");

            if (pair.length != 2) {
                throw new IllegalArgumentException(
                        "Invalid assignments: " + trimmed
                );
            }

            String name = pair[0].trim();
            int value;

            try {
                value = Integer.parseInt(pair[1].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Invalid number in assignment: " + trimmed, e);
            }
            result.put(name, value);
        }

        return result;
    }
}
