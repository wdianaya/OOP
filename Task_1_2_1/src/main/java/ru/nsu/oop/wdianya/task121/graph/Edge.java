package ru.nsu.oop.wdianya.task121.graph;

/**
 * Описание ребра графа.
 */
public record Edge<V>(V from, V to) {
    @Override
    public String toString() {
        return "(%s, %s)".formatted(from, to);
    }
}