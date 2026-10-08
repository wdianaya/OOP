package ru.nsu.oop.impl;

import ru.nsu.oop.graph.AbstractGraph;
import ru.nsu.oop.graph.Edge;
import ru.nsu.oop.graph.Graph;

import java.util.List;

/**
 * Реализация хранения графа в списке смежности.
 */
public class GraphAdjacencyMatrix<T> extends AbstractGraph<T> {
    @Override
    public void addVertex(T value) {

    }

    @Override
    public void delVertex(T value) {

    }

    @Override
    public boolean hasVertex(T value) {
        return false;
    }

    @Override
    public void addEdge(Edge<T> edge) {

    }

    @Override
    public void delEdge(Edge<T> edge) {

    }

    @Override
    public boolean hasEdge(Edge<T> edge) {
        return false;
    }

    @Override
    public int countEdge() {
        return 0;
    }

    @Override
    public List<Edge<T>> getNeighbors(T curVertex) {
        return List.of();
    }
}
