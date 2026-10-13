package ru.nsu.oop.sort;

/**
 * Для выброса ошибки о том что граф содержит цикл.
 */
public class GraphCycleException extends Exception {
    public GraphCycleException(String graphHasCycle) {
        super(graphHasCycle);
    }
}
