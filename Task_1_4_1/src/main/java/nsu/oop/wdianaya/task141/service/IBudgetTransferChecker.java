package nsu.oop.wdianaya.task141.service;

import nsu.oop.wdianaya.task141.model.GradeBook;

/**
 * Для проверки возможности перевода студента на бюджет.
 */
public interface IBudgetTransferChecker {
    /**
     * Проверяет возможность перевода с платной формы обучения на бюджетную.
     */
    public boolean canTransferToBudget(GradeBook gradeBook);
}
