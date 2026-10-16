package ru.nsu.oop.wdianaya.task121.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VertexTest {
    Vertex<Integer> v1 = new Vertex<>(1);

    @Test
    void vertex() {
        assertEquals(1, v1.vertex());
    }

    @Test
    void toStringTest() {
        assertEquals("1", v1.toString());
    }
}