package org.example.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UtilsTest {
    @Test
    void testCheckedRes() {
        assertEquals(GameResults.WIN, GameResults.checkedRes(21));
        assertEquals(GameResults.FAIL, GameResults.checkedRes(22));
        assertEquals(GameResults.FAIL, GameResults.checkedRes(25));
        assertEquals(GameResults.CONT, GameResults.checkedRes(20));
        assertEquals(GameResults.CONT, GameResults.checkedRes(0));
    }
}