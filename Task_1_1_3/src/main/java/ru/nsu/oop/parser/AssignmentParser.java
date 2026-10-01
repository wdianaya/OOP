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
            if (trimmed.isEmpty()) {
                continue;
            }
            int eqIdx = trimmed.indexOf('=');
            if (eqIdx == -1) {
                throw new IllegalArgumentException(
                        "Invalid assignments: " + trimmed
                );
            }

            String name = trimmed.substring(0, eqIdx).trim();
            int value;
            try {
                String valueStr = trimmed.substring(eqIdx+1).trim();
                value = Integer.parseInt(valueStr);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException(
                        "Incorrect number: " + trimmed, ex);
            }
            result.put(name, value);
        }
        return result;
    }
}
