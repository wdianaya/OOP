package nsu.oop.wdianaya.task122.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.Set;

import org.junit.jupiter.api.Test;

import nsu.oop.wdianaya.task122.CustomMap;
import nsu.oop.wdianaya.task122.util.Constants;


class CustomHashTableTest {
    CustomHashTable<Integer, String> table = new CustomHashTable<>();

    @Test
    void addValue() {
        table.addValue(1, "one");
        assertEquals("one", table.getValue(1));
    }

    @Test
    void deleteValue() {
        table.addValue(2, "two");
        table.deleteValue(2);
        assertNull(table.getValue(2));
    }

    @Test
    void updateValue() {
        table.addValue(3, "three");
        table.updateValue(3, "one");
        assertEquals("one", table.getValue(3));
    }

    @Test
    void isExistValue() {
        table.addValue(4, "four");
        assertTrue(table.isExistValue(4));
    }

    @Test
    void entrySet() {
        CustomHashTable<Integer, String> table1 = new CustomHashTable<>();
        Set<CustomMap.CustomEntry<Integer, String>> entrySet = table1.entrySet();
        assertNotNull(entrySet);
        assertEquals(0, entrySet.size());
    }

    @Test
    void entrySetWithElements() {
        CustomHashTable<String, Integer> table1 = new CustomHashTable<>();
        table1.addValue("one", 1);
        table1.addValue("two", 2);
        table1.addValue("three", 3);

        Set<CustomMap.CustomEntry<String, Integer>> entrySet = table1.entrySet();
        assertEquals(3, entrySet.size());

        int count = 0;
        for (CustomMap.CustomEntry<String, Integer> entry : entrySet) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
            count++;
        }
        assertEquals(3, count, "Итератор должен пройти по всем 3 элементам");
    }

    @Test
    void testToString() {
        CustomHashTable<String, Integer> table1 = new CustomHashTable<>();
        String result = table1.toString();
        assertTrue(result.startsWith("CustomHashTable {"));
        assertTrue(result.endsWith("}\n"));
        assertTrue(result.contains("Bucket 0: empty"));
    }

    @Test
    void testEquals() {
        assertTrue(table.equals(table));
    }

    @Test
    void testResizeAndCollisions() {
        for (int i = 0; i < 50; i++) {
            table.addValue(i, "val" + i);
        }

        assertEquals(50, table.entrySet().size());
        assertEquals("val49", table.getValue(49));
        assertEquals("val0", table.getValue(0));

        table.deleteValue(25);
        assertNull(table.getValue(25));
        assertEquals(49, table.entrySet().size());
    }

    @Test
    void entrySetThrowsConcurrentModificationException() {
        table.addValue(1, "one");
        table.addValue(2, "two");
        table.addValue(3, "three");

        assertThrows(ConcurrentModificationException.class, () -> {
            for (CustomMap.CustomEntry<Integer, String> entry : table.entrySet()) {
                table.deleteValue(1);
            }
        });
    }

    @Test
    void testConstants() {
        assertEquals(0.75f, Constants.LOAD_FACTOR);
        assertTrue(Constants.INITIAL_CAPACITY > 0);
    }

}