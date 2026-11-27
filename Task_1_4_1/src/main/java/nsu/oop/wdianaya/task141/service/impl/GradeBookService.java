package nsu.oop.wdianaya.task141.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import nsu.oop.wdianaya.task141.model.GradeBook;
import nsu.oop.wdianaya.task141.model.GradeRecord;
import nsu.oop.wdianaya.task141.service.AverageScoreCalculator;
import nsu.oop.wdianaya.task141.service.BudgetTransferChecker;
import nsu.oop.wdianaya.task141.service.IncreasedScholarshipChecker;
import nsu.oop.wdianaya.task141.service.RedDiplomaPredictor;
import nsu.oop.wdianaya.task141.utils.AcademicGrade;
import nsu.oop.wdianaya.task141.utils.ControlType;
import nsu.oop.wdianaya.task141.utils.CreditGrade;
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
    private static final double PERCENTAGE_OF_EXCELLENT = 0.75;
    private static final double NO_GRADES = 0.0;
    private static final Integer TWO_SEMESTERS = 2;

    @Override
    public double calculateAverageScore(GradeBook gradeBook) {
        return gradeBook.getRecord().values().stream()
                .flatMap(List::stream)
                .map(GradeRecord::grade)
                .filter(grade -> grade != CreditGrade.PASS && grade != CreditGrade.FAIL)
                .mapToInt(AcademicGrade::getValue)
                .average()
                .orElse(NO_GRADES);
    }

    @Override
    public boolean canTransferToBudget(GradeBook gradeBook) {
        Map<Integer, List<GradeRecord>> allRecords = gradeBook.getRecord();
        List<Integer> lastTwoSemesters = allRecords.keySet().stream()
                .sorted(Comparator.reverseOrder())
                .limit(TWO_SEMESTERS)
                .toList();

        boolean hasBadMarks = lastTwoSemesters.stream()
                .flatMap(semester -> allRecords.getOrDefault(
                        semester,
                        Collections.emptyList()).stream())
                .anyMatch(gradeRecord ->
                        gradeRecord.type() == ControlType.EXAM
                                && gradeRecord.grade() == Grade.SATISFACTORY
                );

        return !hasBadMarks;
    }

    @Override
    public boolean isIncreasedScholarship(GradeBook gradeBook, Integer currentSemester) {
        List<GradeRecord> currentGradeRecords = gradeBook.getRecord().getOrDefault(
                currentSemester,
                Collections.emptyList()
        );

        return !currentGradeRecords.isEmpty() && currentGradeRecords.stream()
                .allMatch(gradeRecord -> gradeRecord.grade() == Grade.EXCELLENT
                        || gradeRecord.grade() == CreditGrade.PASS);
    }

    @Override
    public boolean isRedDiplomaPossible(GradeBook gradeBook) {
        Map<Integer, List<GradeRecord>> allRecords = gradeBook.getRecord();

        boolean isDefensePass = allRecords.values().stream()
                .flatMap(List::stream)
                .anyMatch(gradeRecord -> gradeRecord.type() == ControlType.THESIS_DEFENSE
                        && gradeRecord.grade() == Grade.EXCELLENT);

        boolean existSatisfactory = allRecords.values().stream()
                .flatMap(List::stream)
                .anyMatch(gradeRecord -> gradeRecord.grade() == Grade.SATISFACTORY);

        long totalGradedCount = allRecords.values().stream()
                .flatMap(List::stream)
                .map(GradeRecord::grade)
                .filter(grade -> grade != CreditGrade.PASS)
                .count();

        long excellentCount = allRecords.values().stream()
                .flatMap(List::stream)
                .map(GradeRecord::grade)
                .filter(grade -> grade == Grade.EXCELLENT)
                .count();

        if (totalGradedCount == 0) {
            return false;
        }

        double excellentPercentage = (double) excellentCount / totalGradedCount;

        return isDefensePass
                && !existSatisfactory
                && (excellentPercentage >= PERCENTAGE_OF_EXCELLENT);
    }
}
