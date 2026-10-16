package ru.nsu.oop.wdianaya.task121.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EdgeTest {
    Edge<Integer> e1 = new Edge<>(1, 2);

    @Test
    void from() {
        assertEquals(1, e1.from());
    }

    @Test
    void to() {
        assertEquals(2, e1.to());
    }

    @Test
    void toStringTest() {
        assertEquals("(1, 2)", e1.toString());
    }
}