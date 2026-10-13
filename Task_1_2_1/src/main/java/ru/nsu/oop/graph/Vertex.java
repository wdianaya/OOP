package ru.nsu.oop.graph;

/**
 * Описание вершины графа.
 */
public record Vertex<T>(T vertex) {
    @Override
    public String toString() {
        return vertex.toString();
    }
}
