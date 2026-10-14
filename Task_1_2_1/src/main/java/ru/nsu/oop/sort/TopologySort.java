package ru.nsu.oop.sort;

import java.util.List;

import ru.nsu.oop.graph.Graph;

/**
 * Интерфейс топологической сортировки.
 * Если содержит цикл - выбрасывает {@link GraphCycleException}.
 */
public interface TopologySort<T> {
    List<T> sort(Graph<T> graph) throws GraphCycleException;
}
