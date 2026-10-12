package ru.nsu.oop.impl;

import ru.nsu.oop.graph.Edge;
import ru.nsu.oop.graph.Graph;
import ru.nsu.oop.graph.Vertex;

import java.util.*;

/**
 * Реализация хранения графа в матрица смежности.
 */
public class GraphAdjacencyMatrix<T> implements Graph<T> {
    /**
     * Матрица смежности
     */
    private final List<List<Integer>> matrix = new ArrayList<>();
    /**
     * Пул индексов.
     * Очередь для хранения индексов удалённых элементов,
     * чтобы не перестраивать при удалении заново матрицу,
     * а новый при добавлении элемент ставить по индексу одного из удалённых
     */
    private final Deque<Integer> freeIndices = new ArrayDeque<>();
    /**
     * Хранит информацию о том, по какому индексу лежит вершина
     */
    private final HashMap<Vertex<T>, Integer> indexOf = new HashMap<>();
    /**
     * Хранит под индексом вершину:
     * индекс=индекс в матрице нужной вершины.
     * Преимущественно нужно для получения соседей вершины
     */
    private final List<Vertex<T>> vertexByIndex = new ArrayList<>();

    /**
     * При добавлении ребра в матрицу увеличиваем на единицу
     * значение ячейки в матрице смежности
     */
    private void increment(int i, int j) {
        List<Integer> row = matrix.get(i);
        row.set(j, row.get(j) + 1);
    }

    /**
     * При удалении ребра из матрицы уменьшаем на единицу
     * значение ячейки в матрице смежности
     */
    private void decrement(int i, int j) {
        List<Integer> row = matrix.get(i);
        row.set(j, row.get(j) - 1);
    }

    /**
     * Обнуляем всю строку
     */
    private void clearRow(int idx) {
        List<Integer> row = matrix.get(idx);
        for (int j = 0; j < row.size(); j++) {
            row.set(j, 0);
        }
    }

    /**
     * Обнуляем весь столбец
     */
    private void clearColumn(int idx) {
        for (List<Integer> column : matrix) {
            column.set(idx, 0);
        }
    }

    /**
     * Создание нового индекса в матрице
     */
    private int createNewIndex() {
        int lenMatrix = matrix.size();
        int idx = matrix.size();
        // новая строка из нулей
        List<Integer> newRow = new ArrayList<>();
        for (int j = 0; j < lenMatrix + 1; j++) {
            newRow.add(0);
        }
        // в каждую существующую строку - новый столбец
        for (List<Integer> row : matrix) {
            row.add(0);
        }
        matrix.add(newRow);
        return idx;
    }

    @Override
    public void addVertex(Vertex<T> value) {
        if (indexOf.containsKey(value)) {
            return;
        }
        int idx;
        if (!freeIndices.isEmpty()) {
            idx = freeIndices.pop();
            clearRow(idx);
            clearColumn(idx);
            vertexByIndex.set(idx, value);
        } else {
            idx = createNewIndex();
            vertexByIndex.add(value);
        }
        indexOf.put(value, idx);
    }

    @Override
    public void delVertex(Vertex<T> value) {
        Integer idx = indexOf.get(value);
        if (idx == 0) {
            return;
        }

        clearRow(idx);
        clearColumn(idx);

        indexOf.remove(value);
        freeIndices.push(idx);
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

        int idx1 = indexOf.get(from);
        int idx2 = indexOf.get(to);

        increment(idx1, idx2);
        increment(idx2, idx1);
    }

    @Override
    public void delEdge(Edge<T> edge) {
        Vertex<T> from = new Vertex<>(edge.from());
        Vertex<T> to = new Vertex<>(edge.to());

        int idx1 = indexOf.get(from);
        int idx2 = indexOf.get(to);

        decrement(idx1, idx2);
        decrement(idx2, idx1);
    }

    @Override
    public boolean hasEdge(Edge<T> edge) {
        Vertex<T> from = new Vertex<>(edge.from());
        Vertex<T> to = new Vertex<>(edge.to());
        Integer countEdge = matrix.get(indexOf.get(from)).get(indexOf.get(to));
        return countEdge > 0;
    }

    @Override
    public int countEdge() {
        int sum = 0;
        for (List<Integer> row : matrix) {
            for (Integer cell : row) {
                sum += cell;
            }
        }
        return sum / 2;
    }

    @Override
    public List<Vertex<T>> getNeighbors(Vertex<T> curVertex) {
        Integer idx = indexOf.get(curVertex);
        if (idx == null) {
            return List.of();
        }

        List<Vertex<T>> neighbors = new ArrayList<>();
        List<Integer> row = matrix.get(idx);
        for (int i = 0; i < row.size(); ++i) {
            if (row.get(i) > 0) {
                neighbors.add(vertexByIndex.get(i));
            }
        }
        return neighbors;
    }
}
