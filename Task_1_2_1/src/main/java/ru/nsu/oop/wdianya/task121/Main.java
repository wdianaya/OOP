package ru.nsu.oop.wdianya.task121;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import ru.nsu.oop.wdianya.task121.graph.Edge;
import ru.nsu.oop.wdianya.task121.graph.Graph;
import ru.nsu.oop.wdianya.task121.impl.GraphAdjacencyList;
import ru.nsu.oop.wdianya.task121.impl.graphAdjacencyMatrix;
import ru.nsu.oop.wdianya.task121.impl.GraphIncidenceMatrix;
import ru.nsu.oop.wdianya.task121.io.FileGraphReader;
import ru.nsu.oop.wdianya.task121.io.GraphReader;
import ru.nsu.oop.wdianya.task121.sort.topsort.dfs.DfsSorter;
import ru.nsu.oop.wdianya.task121.sort.exceptions.GraphCycleException;
import ru.nsu.oop.wdianya.task121.sort.topsort.TopologySort;

/**
 * Запускает тестовые выводы, чтобы показать
 * работоспособность программы.
 */
public class Main {
    /**
     * Вызывает 3 способа хранения матрицы,
     * читает граф из файла и сортирует его.
     */
    public static void main(String[] args) {
        System.out.println("Матрица инцидентности");
        Graph<Integer> graph3 = new GraphIncidenceMatrix<>();
        graph3.addEdge(new Edge<>(1, 1));
        graph3.addEdge(new Edge<>(1, 2));
        graph3.addEdge(new Edge<>(2, 3));
        graph3.addEdge(new Edge<>(3, 2));
        System.out.println(graph3);

        System.out.println("Матрица смежности");
        Graph<Integer> graph2 = new graphAdjacencyMatrix<>();
        graph2.addEdge(new Edge<>(1, 1));
        graph2.addEdge(new Edge<>(1, 2));
        graph2.addEdge(new Edge<>(2, 3));
        System.out.println(graph2);

        System.out.println("Связный список");
        Graph<Integer> graph1 = new GraphAdjacencyList<>();
        graph1.addEdge(new Edge<>(1, 1));
        graph1.addEdge(new Edge<>(1, 2));
        graph1.addEdge(new Edge<>(2, 3));
        System.out.println(graph1);

        System.out.println("Чтение графа из файла и вывод его топологической сортировки.");
        GraphReader<Integer> reader = new FileGraphReader();
        Graph<Integer> graph4 = new graphAdjacencyMatrix<>();
        try (InputStream in = Main.class.getResourceAsStream("/testfiles/file.txt")) {
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
                throw new Exception(e);
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}