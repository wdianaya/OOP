package ru.nsu.oop.parser;

import ru.nsu.oop.graph.Graph;

import java.io.IOException;
import java.io.Reader;

/**
 * Источник данных для графа.
 */
public interface IOGraphFile<T> {
    /**
     * Заполняет переданный граф данными из источника.
     *
     * @return true — успешно, false — ошибка формата.
     */
    boolean read(Reader source, Graph<T> target) throws IOException;
}
