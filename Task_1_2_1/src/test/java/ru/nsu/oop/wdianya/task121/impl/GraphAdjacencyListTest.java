package ru.nsu.oop.wdianya.task121.impl;

import org.junit.jupiter.api.Test;
import ru.nsu.oop.wdianya.task121.graph.Edge;
import ru.nsu.oop.wdianya.task121.graph.Graph;
import ru.nsu.oop.wdianya.task121.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphAdjacencyListTest {
    Vertex<Integer> v1 = new Vertex<>(1);
    Edge<Integer> e1 = new Edge<>(1, 2);
    Edge<Integer> e2 = new Edge<>(2, 3);


    @Test
    void addVertex() {
        Graph<Integer> graph = new GraphAdjacencyList<>();
        graph.addVertex(v1);
        assertTrue(graph.hasVertex(v1));
    }

    @Test
    void deleteVertex() {
        Graph<Integer> graph = new GraphAdjacencyList<>();
        graph.addVertex(v1);
        graph.deleteVertex(v1);
        assertFalse(graph.hasVertex(v1));
    }

    @Test
    void addEdge() {
        Graph<Integer> graph = new GraphAdjacencyList<>();
        graph.addEdge(e1);
        assertTrue(graph.hasEdge(e1));
    }

    @Test
    void deleteEdge() {
        Graph<Integer> graph = new GraphAdjacencyList<>();
        graph.addEdge(e1);
        graph.deleteEdge(e1);
        assertFalse(graph.hasEdge(e1));
    }

    @Test
    void countEdge() {
        Graph<Integer> graph = new GraphAdjacencyList<>();
        graph.addEdge(e1);
        graph.addEdge(e2);
        assertEquals(2, graph.countEdge());
    }

    @Test
    void getNeighbors() {
        Graph<Integer> graph = new GraphAdjacencyList<>();
        graph.addEdge(new Edge<>(1, 2));
        List<Vertex<Integer>> result = new ArrayList<>();
        for (Vertex<Integer> v : graph.getNeighbors(new Vertex<>(1))) {
            result.add(v);
        }

        List<Vertex<Integer>> expected = List.of(new Vertex<>(2));
        assertEquals(expected, result);
    }

    @Test
    void vertices() {
        Graph<Integer> graph = new GraphAdjacencyList<>();
        graph.addVertex(new Vertex<>(1));

        List<Vertex<Integer>> result = new ArrayList<>();
        for (Vertex<Integer> v : graph.vertices()) {
            result.add(v);
        }

        List<Vertex<Integer>> expected = List.of(new Vertex<>(1));
        assertEquals(expected, result);
    }
}