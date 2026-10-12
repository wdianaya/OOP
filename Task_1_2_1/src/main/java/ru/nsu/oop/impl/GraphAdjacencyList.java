package ru.nsu.oop.impl;

import ru.nsu.oop.graph.Edge;
import ru.nsu.oop.graph.Graph;
import ru.nsu.oop.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Реализация хранения графа в списке смежности.
 */
public class GraphAdjacencyList<T> implements Graph<T> {
    private HashMap<Vertex<T>, List<Vertex<T>>> curGraph = new HashMap<>();

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
        curGraph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
    }

    @Override
    public void delEdge(Edge<T> edge) {
        Vertex<T> from = new Vertex<>(edge.from());
        Vertex<T> to = new Vertex<>(edge.to());

        delVertex(from);
        delVertex(to);
    }

    @Override
    public boolean hasEdge(Edge<T> edge) {
        Vertex<T> from = new Vertex<>(edge.from());
        Vertex<T> to = new Vertex<>(edge.to());
        List<Vertex<T>> neighbors = curGraph.get(from);

        if (neighbors != null && neighbors.contains(to)) {
            return true;
        }
        return false;
    }

    @Override
    public int countEdge() {
        int count = 0;
        for (List<Vertex<T>> neighbors: curGraph.values()) {
            count += neighbors.size();
        }

        // так как в структуре хранится неориентированный граф, делим на два
        return count / 2;
    }

    @Override
    public Iterable<Vertex<T>> getNeighbors(Vertex<T> curVertex) {
        List<Vertex<T>> neighbors = curGraph.get(curVertex);
        if (neighbors == null) {
            return List.of();
        }
        return neighbors;
    }


}
