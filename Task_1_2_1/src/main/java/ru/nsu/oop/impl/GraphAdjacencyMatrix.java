package ru.nsu.oop.impl;

import ru.nsu.oop.graph.Edge;
import ru.nsu.oop.graph.Graph;
import ru.nsu.oop.graph.Vertex;

/**
 * Реализация хранения графа в матрица смежности.
 */
public class GraphAdjacencyMatrix<T> implements Graph<T> {

    @Override
    public void addVertex(Vertex<T> value) {

    }

    @Override
    public void delVertex(Vertex<T> value) {

    }

    @Override
    public boolean hasVertex(Vertex<T> value) {
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
    public Iterable<Vertex<T>> getNeighbors(Vertex<T> curVertex) {
        return null;
    }
}
