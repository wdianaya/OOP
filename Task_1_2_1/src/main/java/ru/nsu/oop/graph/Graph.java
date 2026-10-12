package ru.nsu.oop.graph;

/**
 * Интерфейс основных действия над графом.
 */
public interface Graph<T> {

    void addVertex(Vertex<T> value);

    void delVertex(Vertex<T> value);

    boolean hasVertex(Vertex<T> value);

    void addEdge(Edge<T> edge);

    void delEdge(Edge<T> edge);

    boolean hasEdge(Edge<T> edge);

    int countEdge();

    /**
     * Получить всех соседей вершины.
     */
    Iterable<Vertex<T>> getNeighbors(Vertex<T> curVertex);

}
