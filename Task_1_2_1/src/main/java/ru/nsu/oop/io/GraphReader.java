package ru.nsu.oop.io;

import java.io.IOException;
import java.io.Reader;
import ru.nsu.oop.graph.Graph;

/**
 * Читает граф из источника.
 *
 */
public interface GraphReader<T> {
    /**
     * Читает граф из указанного источника.
     *
     * @param source источник данных
     * @param target граф, в который добавляются вершины и рёбра
     * @throws IOException если источник недоступен или формат нарушен
     */
    void read(Reader source, Graph<T> target) throws IOException;
}