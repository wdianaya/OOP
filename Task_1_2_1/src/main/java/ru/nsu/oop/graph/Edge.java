package ru.nsu.oop.graph;

/**
 * Интерфейс ребра графа.
 */
public interface Edge<T> {
    T from();
    T to();
}
