package ru.nsu.oop;

import ru.nsu.oop.graph.Edge;
import ru.nsu.oop.graph.Graph;
import ru.nsu.oop.impl.GraphAdjacencyList;
import ru.nsu.oop.impl.GraphAdjacencyMatrix;
import ru.nsu.oop.impl.GraphIncidenceMatrix;
import ru.nsu.oop.io.FileGraphReader;
import ru.nsu.oop.io.GraphReader;
import ru.nsu.oop.sort.DfsSorter;
import ru.nsu.oop.sort.GraphCycleException;
import ru.nsu.oop.sort.TopologySort;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * Main класс.
 */
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Graph<Integer> graph1 = new GraphAdjacencyList<>();
        Graph<Integer> graph2 = new GraphAdjacencyMatrix<>();
        Graph<Integer> graph3 = new GraphIncidenceMatrix<>();

        System.out.println("Матрица инцидентности");
        graph3.addEdge(new Edge<>(1, 1));
        graph3.addEdge(new Edge<>(1, 2));
        graph3.addEdge(new Edge<>(2, 4));
        graph3.addEdge(new Edge<>(4, 2));
        graph3.addEdge(new Edge<>(3, 4));
        graph3.addEdge(new Edge<>(1, 3));
        System.out.println(graph3.toString());

        System.out.println("Матрица смежности");
        graph2.addEdge(new Edge<>(1, 1));
        graph2.addEdge(new Edge<>(1, 2));
        graph2.addEdge(new Edge<>(2, 4));


        System.out.println(graph2.toString());

        System.out.println("Связный список");
        graph1.addEdge(new Edge<>(1, 1));
        graph1.addEdge(new Edge<>(1, 2));
        graph1.addEdge(new Edge<>(2, 4));


        System.out.println(graph1.toString());

        System.out.println("Чтение графа из файла и вывод его топологической сортировки.");
        GraphReader<Integer> reader = new FileGraphReader();
        Graph<Integer> graph4 = new GraphAdjacencyMatrix<>();
        try (InputStream in = Main.class.getResourceAsStream("/file.txt")) {
            if (in == null) {
                System.err.println("Файл не найден");
                return;
            }
            try (Reader source = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                reader.read(source, graph4);
                System.out.println(graph4);
                TopologySort<Integer> sortGraph = new DfsSorter<>();
                System.out.println(sortGraph.sort(graph4));
            } catch (GraphCycleException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

    }
}