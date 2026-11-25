package nsu.oop.wdianaya.task141.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import nsu.oop.wdianaya.task141.model.GradeBook;
import nsu.oop.wdianaya.task141.model.Record;
import nsu.oop.wdianaya.task141.service.AverageScoreCalculator;
import nsu.oop.wdianaya.task141.service.BudgetTransferChecker;
import nsu.oop.wdianaya.task141.service.IncreasedScholarshipChecker;
import nsu.oop.wdianaya.task141.service.RedDiplomaPredictor;
import nsu.oop.wdianaya.task141.utils.ControlType;
import nsu.oop.wdianaya.task141.utils.Grade;

/**
 * Сервис для выполнения вычислений и проверок успеваемости студента
 * на основе данных его электронной зачетной книжки.
 */
public class GradeBookService implements
        AverageScoreCalculator,
        BudgetTransferChecker,
        IncreasedScholarshipChecker,
        RedDiplomaPredictor {

    @Override
    public double calculateAverageScore(GradeBook gradeBook) {
        return gradeBook.getRecord().values().stream()
                .flatMap(List::stream)
                .map(Record::grade)
                .filter(grade -> grade != Grade.PASS)
                .mapToInt(Grade::getValue)
                .average()
                .orElse(0.0);
    }

    @Override
    public boolean canTransferToBudget(GradeBook gradeBook) {
        Map<Integer, List<Record>> allRecords = gradeBook.getRecord();
        List<Integer> lastTwoSemesters = allRecords.keySet().stream()
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .toList();

        boolean hasBadMarks = lastTwoSemesters.stream()
                .flatMap(semester -> allRecords.getOrDefault(
                        semester,
                        Collections.emptyList()).stream())
                .anyMatch(record ->
                        record.type() == ControlType.EXAM
                                && record.grade() == Grade.SATISFACTORY
                );

        return !hasBadMarks;
    }

    @Override
    public boolean isIncreasedScholarship(GradeBook gradeBook, Integer currentSemester) {
        List<Record> currentRecords = gradeBook.getRecord().getOrDefault(
                currentSemester,
                Collections.emptyList()
        );

        return !currentRecords.isEmpty() && currentRecords.stream()
                .allMatch(record -> record.grade() == Grade.EXCELLENT
                        || record.grade() == Grade.PASS);
    }

    @Override
    public boolean isRedDiplomaPossible(GradeBook gradeBook) {
        Map<Integer, List<Record>> allRecords = gradeBook.getRecord();

        boolean isDefensePass = allRecords.values().stream()
                .flatMap(List::stream)
                .anyMatch(record -> record.type() == ControlType.THESIS_DEFENSE
                        && record.grade() == Grade.EXCELLENT);

        boolean existSatisfactory = allRecords.values().stream()
                .flatMap(List::stream)
                .anyMatch(record -> record.grade() == Grade.SATISFACTORY);

        long totalGradedCount = allRecords.values().stream()
                .flatMap(List::stream)
                .map(Record::grade)
                .filter(grade -> grade != Grade.PASS)
                .count();

        long excellentCount = allRecords.values().stream()
                .flatMap(List::stream)
                .map(Record::grade)
                .filter(grade -> grade == Grade.EXCELLENT)
                .count();

        if (totalGradedCount == 0) {
            return false;
        }

        double excellentPercentage = (double) excellentCount / totalGradedCount;

        return isDefensePass
                && !existSatisfactory
                && (excellentPercentage >= 0.75);
    }
}
