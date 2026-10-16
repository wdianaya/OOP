package ru.nsu.oop.wdianaya.task121.graph;

/**
 * Описание вершины графа.
 */
public record Vertex<T>(T vertex) {
    @Override
    public String toString() {
        return vertex.toString();
    }
}
