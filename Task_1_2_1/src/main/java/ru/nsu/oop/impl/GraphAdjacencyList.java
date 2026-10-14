package ru.nsu.oop.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.nsu.oop.graph.Edge;
import ru.nsu.oop.graph.Graph;
import ru.nsu.oop.graph.Vertex;

/**
 * Реализация хранения графа в списке смежности.
 */
public class GraphAdjacencyList<T> implements Graph<T> {
    /**
     * Список смежности. Вершина -> её список соседей.
     */
    private final Map<Vertex<T>, List<Vertex<T>>> curGraph = new HashMap<>();

    @Override
    public void addVertex(Vertex<T> value) {
        if (!hasVertex(value)) {
            curGraph.put(value, new ArrayList<>());
        }
    }

    @Override
    public void delVertex(Vertex<T> value) {
        if (hasVertex(value)) {
            List<Vertex<T>> child = curGraph.get(value);
            for (Vertex<T> neighbor : child) {
                List<Vertex<T>> neighborList = curGraph.get(neighbor);
                if (neighborList != null) {
                    neighborList.remove(value);
                }
            }
            curGraph.remove(value);
        }
    }

    @Override
    public boolean hasVertex(Vertex<T> value) {
        return curGraph.containsKey(value);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        Vertex<T> from = new Vertex<>(edge.from());
        Vertex<T> to = new Vertex<>(edge.to());

        curGraph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        curGraph.computeIfAbsent(to, k -> new ArrayList<>());
    }

    @Override
    public void delEdge(Edge<T> edge) {
        Vertex<T> from = new Vertex<>(edge.from());
        Vertex<T> to = new Vertex<>(edge.to());

        List<Vertex<T>> fromNeighbors = curGraph.get(from);
        List<Vertex<T>> toNeighbors = curGraph.get(to);
        if (fromNeighbors == null || toNeighbors == null) {
            return;
        }

        fromNeighbors.remove(to);
        toNeighbors.remove(from);
    }

    @Override
    public boolean hasEdge(Edge<T> edge) {
        Vertex<T> from = new Vertex<>(edge.from());
        Vertex<T> to = new Vertex<>(edge.to());
        List<Vertex<T>> neighbors = curGraph.get(from);

        return neighbors != null && neighbors.contains(to);
    }

    @Override
    public int countEdge() {
        int count = 0;
        for (List<Vertex<T>> neighbors : curGraph.values()) {
            count += neighbors.size();
        }
        return count;
    }

    @Override
    public Iterable<Vertex<T>> getNeighbors(Vertex<T> curVertex) {
        List<Vertex<T>> neighbors = curGraph.get(curVertex);
        if (neighbors == null) {
            return List.of();
        }
        return neighbors;
    }

    @Override
    public Iterable<Vertex<T>> vertices() {
        return curGraph.keySet();
    }

    @Override
    public String toString() {
        var string = new StringBuilder();
        var curVertex = 0;
        for (var key : curGraph.keySet()) {
            string.append("v%d  %s: ".formatted(++curVertex, key.vertex()));
            var neighbors = curGraph.get(key);
            for (var val : neighbors) {
                string.append(val.vertex());
                string.append(", ");
            }
            string.append('\n');
        }

        return string.toString();
    }
}
