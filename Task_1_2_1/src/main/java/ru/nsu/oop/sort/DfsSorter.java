package ru.nsu.oop.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.nsu.oop.graph.Graph;
import ru.nsu.oop.graph.Vertex;

/**
 * Алгоритм топологической сортиорвки через поиск в глубину.
 */
public class DfsSorter<T> implements TopologySort<T> {
    /**
     * Реализация поиска в глубину.
     */
    private void dfs(Vertex<T> vertex, Map<Vertex<T>, DfsColors> vertexColor,
                     List<T> result, Graph<T> curGraph) throws GraphCycleException {
        vertexColor.put(vertex, DfsColors.GRAY);
        List<Vertex<T>> neighbors = (List<Vertex<T>>) curGraph.getNeighbors(vertex);
        for (Vertex<T> eachVertex : neighbors) {
            if (vertexColor.get(eachVertex).equals(DfsColors.GRAY)) {
                throw new GraphCycleException("Graph has cycle");
            }
            if (vertexColor.get(eachVertex).equals(DfsColors.WHITE)) {
                dfs(eachVertex, vertexColor, result, curGraph);
            }
        }

        vertexColor.put(vertex, DfsColors.BLACK);
        result.add(vertex.vertex());
    }

    /**
     * Вызов dfs и вывод результата топологической сортировки.
     */
    @Override
    public List<T> sort(Graph<T> graph) throws GraphCycleException {
        if (graph == null) {
            return List.of();
        }
        Map<Vertex<T>, DfsColors> vertexColor = new HashMap<>();
        List<T> result = new ArrayList<>();

        List<Vertex<T>> allVertex = new ArrayList<>();
        for (Vertex<T> vertex : graph.vertices()) {
            allVertex.add(vertex);
        }

        for (Vertex<T> vertex : allVertex) {
            vertexColor.put(vertex, DfsColors.WHITE);
        }

        for (Vertex<T> vertex : allVertex) {
            if (vertexColor.get(vertex) == DfsColors.WHITE) {
                dfs(vertex, vertexColor, result, graph);
            }
        }
        Collections.reverse(result);
        return result;
    }
}
