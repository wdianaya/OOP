package nsu.oop.wdianaya.task122.impl;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nsu.oop.wdianaya.task122.CustomMap;


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

}