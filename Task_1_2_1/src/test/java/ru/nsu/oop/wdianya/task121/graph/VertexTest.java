package ru.nsu.oop.wdianya.task121.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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