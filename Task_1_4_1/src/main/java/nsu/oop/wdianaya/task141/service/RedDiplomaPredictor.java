package nsu.oop.wdianaya.task141.service;

import nsu.oop.wdianaya.task141.model.GradeBook;

/**
 * Для прогнозирования возможности получения диплома с отличием.
 */
public interface RedDiplomaPredictor {
    /**
     * Прогнозирует возможность получения красного диплома.
     */
    public boolean isRedDiplomaPossible(GradeBook gradeBook);
}
