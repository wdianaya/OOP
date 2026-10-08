package ru.nsu.oop.graph;

import java.util.List;

/**
 * Интерфейс основных действия над графом.
 */
public interface Graph<T> {
    void addVertex(T value);

    void delVertex(T value);

    boolean hasVertex(T value);

    void addEdge(Edge<T> edge);

    void delEdge(Edge<T> edge);

    boolean hasEdge(Edge<T> edge);

    int countEdge();

    /**
     * Получить всех соседей вершины.
     */
    List<Edge<T>> getNeighbors(T curVertex);

    @Override
    boolean equals(Object value);

    @Override
    int hashCode();

    @Override
    String toString();
}
