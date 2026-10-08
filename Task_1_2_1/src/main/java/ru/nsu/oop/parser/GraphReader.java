package ru.nsu.oop.parser;

import ru.nsu.oop.graph.Graph;

import java.io.IOException;
import java.io.Reader;

/**
 * Чтение данных о графе из файла.
 */
public class GraphReader<T>  implements IOGraphFile<T> {
    @Override
    public boolean read(Reader source, Graph<T> target) throws IOException {
        return false;
    }
}
