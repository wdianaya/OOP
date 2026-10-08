package ru.nsu.oop.sort;


import ru.nsu.oop.graph.Graph;

import java.util.List;

/**
 * Интерфейс топологической сортировки.
 * Если содержит цикл - выбрасывает {@link GraphCycleException}.
 */
public interface TopologySort<T> {
    List<T> sort(Graph<T> graph) throws GraphCycleException;
}
