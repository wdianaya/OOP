package ru.nsu.oop.wdianya.task121.graph;

import org.junit.jupiter.api.Test;
import ru.nsu.oop.wdianya.task121.impl.GraphIncidenceMatrix;

import static org.junit.jupiter.api.Assertions.*;

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
}