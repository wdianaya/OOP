package ru.nsu.oop.wdianya.task121.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.nsu.oop.wdianya.task121.graph.Edge;
import ru.nsu.oop.wdianya.task121.graph.Graph;
import ru.nsu.oop.wdianya.task121.graph.Vertex;

/**
 * Реализация хранения графа в матрица смежности.
 */
public class graphAdjacencyMatrix<T> implements Graph<T> {
    /**
     * Матрица смежности. matrix[i][j] — число рёбер между i и j.
     */
    private final List<List<Integer>> matrix = new ArrayList<>();
    /**
     * Пул свободных индексов.
     * Очередь для хранения индексов удалённых элементов,
     * чтобы не перестраивать при удалении заново матрицу,
     * а новый при добавлении элемент ставить по индексу одного из удалённых.
     */
    private final Deque<Integer> freeIndices = new ArrayDeque<>();
    /**
     * Хранит информацию о том, по какому индексу лежит вершина.
     */
    private final Map<Vertex<T>, Integer> indexOf = new HashMap<>();
    /**
     * Хранит под индексом вершину.
     * индекс=индекс в матрице нужной вершины.
     * Преимущественно нужно для получения соседей вершины.
     */
    private final List<Vertex<T>> vertexByIndex = new ArrayList<>();

    @Override
    public void addVertex(Vertex<T> value) {
        if (indexOf.containsKey(value)) {
            return;
        }
        int vertexIdx;
        if (!freeIndices.isEmpty()) {
            // переиспользуем освободившийся индекс - обнуляем его строку и столбец
            vertexIdx = freeIndices.pop();
            clearRow(vertexIdx);
            clearColumn(vertexIdx);
            vertexByIndex.set(vertexIdx, value);
        } else {
            vertexIdx = createNewIndex();
            vertexByIndex.add(value);
        }
        indexOf.put(value, vertexIdx);
    }

    @Override
    public void deleteVertex(Vertex<T> value) {
        Integer idx = indexOf.get(value);
        if (idx == null) {
            return;
        }
        // удаляем все рёбра, инцидентные вершине
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

        int fromIdx = indexOf.get(from);
        int toIdx = indexOf.get(to);

        increment(fromIdx, toIdx);
    }

    @Override
    public void deleteEdge(Edge<T> edge) {
        Vertex<T> from = new Vertex<>(edge.from());
        Vertex<T> to = new Vertex<>(edge.to());

        Integer fromIdx = indexOf.get(from);
        Integer toIdx = indexOf.get(to);
        if (fromIdx == null || toIdx == null) {
            return;
        }
        if (matrix.get(fromIdx).get(toIdx) == 0) {
            return;
        }
        decrement(fromIdx, toIdx);
    }

    @Override
    public boolean hasEdge(Edge<T> edge) {
        Vertex<T> from = new Vertex<>(edge.from());
        Vertex<T> to = new Vertex<>(edge.to());

        Integer fromIdx = indexOf.get(from);
        Integer toIdx = indexOf.get(to);
        if (fromIdx == null || toIdx == null) {
            return false;
        }

        return matrix.get(fromIdx).get(toIdx) > 0;
    }

    @Override
    public int countEdge() {
        int count = 0;
        int n = matrix.size();
        for (int i = 0; i < n; i++) {
            List<Integer> row = matrix.get(i);
            for (int j = 0; j < n; j++) {
                count += row.get(j);
            }
        }
        return count;
    }

    @Override
    public List<Vertex<T>> getNeighbors(Vertex<T> curVertex) {
        Integer vertexIdx = indexOf.get(curVertex);
        if (vertexIdx == null) {
            return List.of();
        }

        List<Vertex<T>> neighbors = new ArrayList<>();
        List<Integer> row = matrix.get(vertexIdx);
        for (int neighborIdx = 0; neighborIdx < row.size(); neighborIdx++) {
            if (row.get(neighborIdx) > 0) {
                neighbors.add(vertexByIndex.get(neighborIdx));
            }
        }
        return neighbors;
    }

    @Override
    public Iterable<Vertex<T>> vertices() {
        return indexOf.keySet();
    }

    @Override
    public String toString() {
        var resultString = new StringBuilder("   ");
        int sizeMatrix = matrix.size();
        for (int col = 0; col < sizeMatrix; ++col) {
            resultString.append("v%d ".formatted(col + 1));
        }
        resultString.append('\n');
        for (int row = 0; row < sizeMatrix; ++row) {
            resultString.append("v%d ".formatted(row + 1));
            List<Integer> curRow = matrix.get((int) row);
            for (Integer elem : curRow) {
                resultString.append(elem);
                resultString.append("  ");
            }
            resultString.append('\n');
        }
        return resultString.toString();
    }

    /**
     * Увеличивает счётчик рёбер fromIdx -> toIdx на 1.
     */
    private void increment(int fromIdx, int toIdx) {
        List<Integer> row = matrix.get(fromIdx);
        row.set(toIdx, row.get(toIdx) + 1);
    }

    /**
     * Уменьшает счётчик рёбер fromIdx -> toIdx на 1.
     */
    private void decrement(int fromIdx, int toIdx) {
        List<Integer> row = matrix.get(fromIdx);
        row.set(toIdx, row.get(toIdx) - 1);
    }

    /**
     * Обнуляем всю строку rowIdx.
     */
    private void clearRow(int rowIdx) {
        List<Integer> row = matrix.get(rowIdx);
        for (int colIdx = 0; colIdx < row.size(); colIdx++) {
            row.set(colIdx, 0);
        }
    }

    /**
     * Обнуляем весь столбец colIdx.
     */
    private void clearColumn(int colIdx) {
        for (List<Integer> row : matrix) {
            row.set(colIdx, 0);
        }
    }

    /**
     * Расширяет матрицу на одну строку и один столбец, возвращает индекс
     * новой вершины.
     */
    private int createNewIndex() {
        int idx = matrix.size();

        // новая строка из idx + 1 нулей
        List<Integer> newRow = new ArrayList<>();
        for (int colIdx = 0; colIdx <= idx; colIdx++) {
            newRow.add(0);
        }
        // в каждую существующую строку - новый столбец
        for (List<Integer> row : matrix) {
            row.add(0);
        }
        matrix.add(newRow);
        return idx;
    }
}
