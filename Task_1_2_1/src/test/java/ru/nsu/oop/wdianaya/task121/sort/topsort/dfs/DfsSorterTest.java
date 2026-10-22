package ru.nsu.oop.wdianaya.task121.sort.topsort.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Test;
import ru.nsu.oop.wdianaya.task121.graph.Graph;
import ru.nsu.oop.wdianaya.task121.impl.GraphAdjacencyMatrix;
import ru.nsu.oop.wdianaya.task121.io.FileGraphReader;
import ru.nsu.oop.wdianaya.task121.io.GraphReader;
import ru.nsu.oop.wdianaya.task121.sort.exceptions.GraphCycleException;
import ru.nsu.oop.wdianaya.task121.sort.topsort.TopologySort;

class DfsSorterTest {

    @Test
    void sort() {
        GraphReader<Integer> reader = new FileGraphReader();
        Graph<Integer> graph4 = new GraphAdjacencyMatrix<>();
        try (InputStream in = DfsSorter.class.getResourceAsStream("/testfiles/testFile1.txt")) {
            if (in == null) {
                return;
            }
            try (Reader source = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                reader.read(source, graph4);
                TopologySort<Integer> sortGraph = new DfsSorter<>();
                List<Integer> result = sortGraph.sort(graph4);
                List<Integer> expected = List.of(1, 2, 3);
                assertEquals(result, expected);
            } catch (GraphCycleException e) {
                throw new Exception(e);
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}