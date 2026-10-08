package ru.nsu.oop.sort;

import ru.nsu.oop.graph.Graph;

import java.util.List;

/**
 * Топологическая сортировка через алгоритм Кана.
 */
public class KahnSorter<T> implements TopologySort<T> {
    @Override
    public List<T> sort(Graph<T> graph) {
        return List.of();
    }
}
