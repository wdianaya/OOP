package org.example.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UtilsTest {
    @Test
    void testCheckedRes() {
        assertEquals(Utils.WIN, Utils.checkedRes(21));
        assertEquals(Utils.FAIL, Utils.checkedRes(22));
        assertEquals(Utils.FAIL, Utils.checkedRes(25));
        assertEquals(Utils.CONT, Utils.checkedRes(20));
        assertEquals(Utils.CONT, Utils.checkedRes(0));
    }
}