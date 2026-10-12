package ru.nsu.oop.impl;

import ru.nsu.oop.graph.Edge;
import ru.nsu.oop.graph.Graph;
import ru.nsu.oop.graph.Vertex;

import java.util.*;

/**
 * Реализация хранения графа в матрице инцидентности.
 */
public class GraphIncidenceMatrix<T> implements Graph<T> {
    /**
     * Матрица инцидентности: строки - вершины, столбцы - рёбра.
     * matrix[v][e] != 0, если вершина v инцидентна ребру e.
     */
    private final List<List<Integer>> matrix = new ArrayList<>();
    /**
     * Хранит вершины, их индекс - индекс строки в матрице
     */
    private final List<Vertex<T>> vertexByIndex = new ArrayList<>();
    /**
     * Хранит ребра, их индекс - индекс столбца в матрице
     */
    private final List<Edge<T>> edgeByIndex = new ArrayList<>();
    /**
     * Хранит информацию о том, по какому индексу лежит вершина
     */
    private final Map<Vertex<T>, Integer> indexOf = new HashMap<>();

    /**
     * Удаляет столбец e из матрицы и списка рёбер.
     */
    private void removeEdgeColumn(int edgeIdx) {
        for (List<Integer> row : matrix) {
            row.remove((int) edgeIdx);
        }
        edgeByIndex.remove((int) edgeIdx);
    }

    /**
     * Увеличивает на 1 счётчик инцидентности вершины vertexIdx ребру edgeIdx
     */
    private void increment(int vertexIdx, int edgeIdx) {
        List<Integer> row = matrix.get(vertexIdx);
        row.set(edgeIdx, row.get(edgeIdx) + 1);
    }

    /**
     * Пересчитывает индексы в indexOf после удаления строки deletedVertexIdx:
     * у всех вершин, которые были ниже, индекс уменьшился на 1.
     */
    private void updateIdxOfVertex(int deletedVertexIdx) {
        int lenVertex = vertexByIndex.size();
        for (int vertexIdx = deletedVertexIdx; vertexIdx < lenVertex; ++vertexIdx) {
            Vertex<T> curVertex = vertexByIndex.get(vertexIdx);
            indexOf.put(curVertex, vertexIdx);
        }
    }

    @Override
    public void addVertex(Vertex<T> value) {
        if (indexOf.containsKey(value)) {
            return;
        }
        int lenColumn = edgeByIndex.size();
        List<Integer> newRaw = new ArrayList<>();
        for (int colIdx = 0; colIdx < lenColumn; ++colIdx) {
            newRaw.add(0);
        }
        vertexByIndex.add(value);
        matrix.add(newRaw);
        indexOf.put(value, matrix.size() - 1);
    }

    @Override
    public void delVertex(Vertex<T> value) {
        Integer idx = indexOf.get(value);
        if (idx == null) {
            return;
        }

        List<Integer> row = matrix.get(idx);
        // Идём с конца, т.к. removeEdgeColumn удаляет столбец и сдвигает индексы
        // всех столбцов правее влево. При движении с начала мы бы пропустили часть рёбер.
        for (int edgeIdx = row.size() - 1; edgeIdx >= 0; edgeIdx--) {
            if (row.get(edgeIdx) != 0) {
                removeEdgeColumn(edgeIdx);
            }
        }

        matrix.remove((int) idx);
        vertexByIndex.remove((int) idx);
        indexOf.remove(value);

        updateIdxOfVertex(idx);
    }

    @Override
    public boolean hasVertex(Vertex<T> value) {
        return indexOf.containsKey(value);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        Vertex<T> from = new Vertex<>(edge.from());
        Vertex<T> to = new Vertex<>(edge.to());

        addVertex(from);
        addVertex(to);

        int newEdgeIdx = edgeByIndex.size();
        edgeByIndex.add(edge);
        for (List<Integer> row : matrix) {
            row.add(0);
        }

        // Граф неориентированный- ребро инцидентно обеим вершинам,
        // поэтому отмечаем его в обеих строках
        int fromIdx = indexOf.get(from);
        int toIdx = indexOf.get(to);
        increment(fromIdx, newEdgeIdx);
        increment(toIdx, newEdgeIdx);
    }

    @Override
    public void delEdge(Edge<T> curEdge) {
        Vertex<T> from = new Vertex<>(curEdge.from());
        Vertex<T> to = new Vertex<>(curEdge.to());
        Integer fromIdx = indexOf.get(from);
        Integer toIdx = indexOf.get(to);
        if (fromIdx == null || toIdx == null) {
            return;
        }
        List<Integer> rowFrom = matrix.get(fromIdx);
        List<Integer> rowTo = matrix.get(toIdx);
        for (int edgeIdx = 0; edgeIdx < edgeByIndex.size(); edgeIdx++) {
            if (rowFrom.get(edgeIdx) != 0 && rowTo.get(edgeIdx) != 0) {
                removeEdgeColumn(edgeIdx);
                return; // Удаляем одно ребро, первое встречное
            }
        }
    }

    @Override
    public boolean hasEdge(Edge<T> curEdge) {
        Vertex<T> from = new Vertex<>(curEdge.from());
        Vertex<T> to = new Vertex<>(curEdge.to());
        Integer fromIdx = indexOf.get(from);
        Integer toIdx = indexOf.get(to);
        if (fromIdx == null || toIdx == null) {
            return false;
        }
        List<Integer> rowFrom = matrix.get(fromIdx);
        List<Integer> rowTo = matrix.get(toIdx);
        for (int edgeIdx = 0; edgeIdx < edgeByIndex.size(); edgeIdx++) {
            if (rowFrom.get(edgeIdx) != 0 && rowTo.get(edgeIdx) != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int countEdge() {
        return edgeByIndex.size();
    }

    @Override
    public List<Vertex<T>> getNeighbors(Vertex<T> curVertex) {
        Integer idx = indexOf.get(curVertex);
        if (idx == null) {
            return List.of();
        }
        // LinkedHashSet чтобы убрать дубликаты соседей при кратных рёбрах
        // и сохранить порядок обхода
        Set<Vertex<T>> neighbors = new LinkedHashSet<>();
        List<Integer> row = matrix.get(idx);
        for (int edgeIdx = 0; edgeIdx < row.size(); edgeIdx++) {
            if (row.get(edgeIdx) != 0) {
                Edge<T> curEdge = edgeByIndex.get(edgeIdx);
                // curVertex может быть как from, так и to, поэтому
                // рассматриваем оба случая
                T other = curEdge.from().equals(curVertex.vertex())
                        ? curEdge.to()
                        : curEdge.from();
                neighbors.add(new Vertex<>(other));
            }
        }
        return new ArrayList<>(neighbors);
    }
}
