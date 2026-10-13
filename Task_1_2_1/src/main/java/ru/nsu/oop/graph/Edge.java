package ru.nsu.oop.graph;

/**
 * Описание ребра графа.
 */
public record Edge<V>(V from, V to) {
    @Override
    public String toString() {
        return "(%s, %s)".formatted(from, to);
    }
}