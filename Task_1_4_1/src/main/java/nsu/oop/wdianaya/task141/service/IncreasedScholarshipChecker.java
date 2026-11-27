package nsu.oop.wdianaya.task141.service;

import nsu.oop.wdianaya.task141.model.GradeBook;

/**
 * Для проверки права на получение повышенной стипендии.
 */

public interface IncreasedScholarshipChecker {
    /**
     * Проверяет возможность получения повышенной стипендии в текущем семестре.
     */
    boolean isIncreasedScholarship(GradeBook gradeBook, Integer currentSemester);
}
