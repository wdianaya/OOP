package ru.nsu.oop.wdianya.task121.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import ru.nsu.oop.wdianya.task121.graph.Edge;
import ru.nsu.oop.wdianya.task121.graph.Graph;
import ru.nsu.oop.wdianya.task121.graph.Vertex;


/**
 * Читает граф из текстового файла фиксированного формата.
 * Формат:
 * N - число вершин
 * u1 v1 - ребро (u1, v1)
 * u2 v2
 * ...
 * Вершины нумеруются целыми числами. Пустые строки игнорируются.
 *
 */
public class FileGraphReader implements GraphReader<Integer> {
    @Override
    public void read(Reader source, Graph<Integer> target) throws IOException {
        try (BufferedReader reader = new BufferedReader(source)) {
            String firstLine = reader.readLine();
            if (firstLine == null) {
                return;
            }

            int vertexCount = parseVertexCount(firstLine);

            for (int i = 1; i <= vertexCount; i++) {
                target.addVertex(new Vertex<>(i));
            }

            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                List<Integer> tokens = parseTokens(line);
                if (tokens.isEmpty()) {
                    continue;
                }

                if (tokens.size() != 2) {
                    throw new IOException("Некорректное количество"
                            + " аргементов на строке:"
                            + lineNumber);
                }

                target.addEdge(new Edge<>(tokens.get(0), tokens.get(1)));
            }
        }
    }

    private List<Integer> parseTokens(String line) throws IOException {
        List<Integer> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < line.length(); ++i) {
            char symbol = line.charAt(i);
            if (Character.isWhitespace(symbol)) {
                if (token.length() > 0) {
                    tokens.add(parseIntToken(token.toString()));
                    token.setLength(0);
                }
            } else {
                token.append(symbol);
            }

        }
        if (token.length() > 0) {
            tokens.add(parseIntToken(token.toString()));
            token.setLength(0);
        }
        return tokens;
    }

    private Integer parseIntToken(String token) throws IOException {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IOException("Не число: " + token, e);
        }
    }

    private int parseVertexCount(String line) throws IOException {
        try {
            int count = Integer.parseInt(line.trim());
            if (count < 0) {
                throw new IOException("Число вершин не может быть < 0: " + count);
            }
            return count;
        } catch (NumberFormatException e) {
            throw new IOException("Ошибка при чтении количества вершин: " + line, e);
        }
    }
}
