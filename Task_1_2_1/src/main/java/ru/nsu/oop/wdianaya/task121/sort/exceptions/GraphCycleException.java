package ru.nsu.oop.wdianaya.task121.sort.exceptions;

/**
 * Для выброса ошибки о том что граф содержит цикл.
 */
public class GraphCycleException extends Exception {
    public GraphCycleException(String graphHasCycle) {
        super(graphHasCycle);
    }
}
