package ru.nsu.oop.sort;

import ru.nsu.oop.graph.Graph;

import java.util.List;

/**
 * Алгоритм топологической сортиорвки через поиск в глубину.
 */
public class DfsSorter<T> implements TopologySort<T> {
    @Override
    public List<T> sort(Graph<T> graph) {
        return List.of();
    }
}
