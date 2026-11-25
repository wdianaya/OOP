package nsu.oop.wdianaya.task141.service;

import nsu.oop.wdianaya.task141.model.GradeBook;

/**
 * Для вычисления текущего среднего балла студента.
 */
public interface IAverageScoreCalculator {
    /**
     * Вычисляет текущий средний балл за все время обучения.
     */
    public double calculateAverageScore(GradeBook gradeBook);
}
