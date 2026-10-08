package ru.nsu.oop.parser;

import ru.nsu.oop.graph.Graph;

import java.io.IOException;
import java.io.Reader;

/**
 * Класс, реализовывающий вывод в файл.
 */
public class GraphWriter<T> implements IOGraphFile<T> {

    @Override
    public boolean read(Reader source, Graph<T> target) throws IOException {
        return false;
    }
}
