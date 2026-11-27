package nsu.oop.wdianaya.task141.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nsu.oop.wdianaya.task141.model.GradeBook;
import nsu.oop.wdianaya.task141.model.GradeRecord;
import nsu.oop.wdianaya.task141.utils.ControlType;
import nsu.oop.wdianaya.task141.utils.CreditGrade;
import nsu.oop.wdianaya.task141.utils.Grade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookServiceTest {
    private GradeBookService service;
    private GradeBook gradeBook;

    @BeforeEach
    void setUp() {
        service = new GradeBookService();
        gradeBook = new GradeBook();
    }

    @Test
    void calculateAverageScore_EmptyGradeBook_ReturnsZero() {
        assertEquals(0.0, service.calculateAverageScore(gradeBook));
    }

    @Test
    void calculateAverageScore_OnlyCredits_ReturnsZero() {
        gradeBook.addRecord(1, new GradeRecord(
                "Физра",
                ControlType.CREDIT,
                CreditGrade.PASS)
        );
        assertEquals(0.0, service.calculateAverageScore(gradeBook));
    }

    @Test
    void calculateAverageScore_MixedGrades_CalculatesCorrectly() {
        gradeBook.addRecord(1, new GradeRecord(
                "Математика",
                ControlType.EXAM,
                Grade.EXCELLENT)
        ); // 5
        gradeBook.addRecord(1, new GradeRecord(
                "Физика",
                ControlType.EXAM,
                Grade.GOOD)
        ); // 4
        gradeBook.addRecord(1, new GradeRecord(
                "История",
                ControlType.DIFF_CREDIT,
                Grade.SATISFACTORY)
        ); // 3
        gradeBook.addRecord(1, new GradeRecord(
                "Физра",
                ControlType.CREDIT,
                CreditGrade.PASS)
        ); // Игнорируется

        // (5 + 4 + 3) / 3 = 4.0
        assertEquals(4.0, service.calculateAverageScore(gradeBook));
    }

    // ==================== 2. ТЕСТЫ ПЕРЕВОДА НА БЮДЖЕТ ====================

    @Test
    void canTransferToBudget_NoBadGrades_ReturnsTrue() {
        gradeBook.addRecord(1, new GradeRecord(
                "Математика",
                ControlType.EXAM,
                Grade.EXCELLENT)
        );
        gradeBook.addRecord(2, new GradeRecord(
                "Физика",
                ControlType.EXAM,
                Grade.GOOD)
        );
        assertTrue(service.canTransferToBudget(gradeBook));
    }

    @Test
    void canTransferToBudget_SatisfactoryInDiffCredit_ReturnsTrue() {
        gradeBook.addRecord(1, new GradeRecord(
                "История",
                ControlType.DIFF_CREDIT,
                Grade.SATISFACTORY)
        );
        gradeBook.addRecord(2, new GradeRecord(
                "Философия",
                ControlType.DIFF_CREDIT,
                Grade.SATISFACTORY)

        );
        assertTrue(service.canTransferToBudget(gradeBook));
    }

    @Test
    void canTransferToBudget_SatisfactoryInExamLastSemester_ReturnsFalse() {
        gradeBook.addRecord(1, new GradeRecord(
                "Математика", ControlType.EXAM, Grade.EXCELLENT));
        gradeBook.addRecord(2, new GradeRecord(
                "Физика", ControlType.EXAM, Grade.SATISFACTORY));
        assertFalse(service.canTransferToBudget(gradeBook));
    }

    @Test
    void canTransferToBudget_BadGradeInOldSemester_ReturnsTrue() {
        gradeBook.addRecord(1, new GradeRecord(
                "Математика", ControlType.EXAM, Grade.SATISFACTORY));
        gradeBook.addRecord(2, new GradeRecord(
                "Физика", ControlType.EXAM, Grade.GOOD));
        gradeBook.addRecord(3, new GradeRecord(
                "Информатика", ControlType.EXAM, Grade.EXCELLENT));
        assertTrue(service.canTransferToBudget(gradeBook));
    }

    // ==================== 3. ТЕСТЫ СТИПЕНДИИ ====================

    @Test
    void isIncreasedScholarship_EmptySemester_ReturnsFalse() {
        assertFalse(service.isIncreasedScholarship(gradeBook, 1));
    }

    @Test
    void isIncreasedScholarship_AllExcellentAndCredits_ReturnsTrue() {
        gradeBook.addRecord(1, new GradeRecord(
                "Математика", ControlType.EXAM, Grade.EXCELLENT));
        gradeBook.addRecord(1, new GradeRecord(
                "Физра", ControlType.CREDIT, CreditGrade.PASS));
        assertTrue(service.isIncreasedScholarship(gradeBook, 1));
    }

    @Test
    void isIncreasedScholarship_HasGoodGrade_ReturnsFalse() {
        gradeBook.addRecord(1, new GradeRecord(
                "Математика", ControlType.EXAM, Grade.EXCELLENT));
        gradeBook.addRecord(1, new GradeRecord(
                "Физика", ControlType.EXAM, Grade.GOOD)); // 4-ка
        assertFalse(service.isIncreasedScholarship(gradeBook, 1));
    }

    // ==================== 4. ТЕСТЫ КРАСНОГО ДИПЛОМА ====================

    @Test
    void isRedDiplomaPossible_NoVkr_ReturnsFalse() {
        // Нет защиты ВКР, даже если всё отлично
        gradeBook.addRecord(1, new GradeRecord(
                "Математика", ControlType.EXAM, Grade.EXCELLENT));
        assertFalse(service.isRedDiplomaPossible(gradeBook));
    }

    @Test
    void isRedDiplomaPossible_HasSatisfactory_ReturnsFalse() {
        gradeBook.addRecord(1, new GradeRecord(
                "Математика", ControlType.EXAM, Grade.SATISFACTORY));
        gradeBook.addRecord(8, new GradeRecord(
                "ВКР", ControlType.THESIS_DEFENSE, Grade.EXCELLENT));
        assertFalse(service.isRedDiplomaPossible(gradeBook));
    }

    @Test
    void isRedDiplomaPossible_LessThan75PercentExcellent_ReturnsFalse() {
        // 2 отлично из 4 оценок = 50% (нужно 75%)
        gradeBook.addRecord(1, new GradeRecord(
                "Математика", ControlType.EXAM, Grade.EXCELLENT));
        gradeBook.addRecord(1, new GradeRecord(
                "Физика", ControlType.EXAM, Grade.GOOD));
        gradeBook.addRecord(8, new GradeRecord(
                "ВКР", ControlType.THESIS_DEFENSE, Grade.EXCELLENT));
        gradeBook.addRecord(8, new GradeRecord(
                "Практика", ControlType.PRACTICE_DEFENSE, Grade.GOOD));

        assertFalse(service.isRedDiplomaPossible(gradeBook));
    }

    @Test
    void isRedDiplomaPossible_PerfectConditions_ReturnsTrue() {
        // 3 отлично из 3 оценок (100% > 75%), нет троек, ВКР отлично
        gradeBook.addRecord(1, new GradeRecord(
                "Математика", ControlType.EXAM, Grade.EXCELLENT));
        gradeBook.addRecord(2, new GradeRecord(
                "Физика", ControlType.EXAM, Grade.EXCELLENT));
        gradeBook.addRecord(8, new GradeRecord(
                "ВКР", ControlType.THESIS_DEFENSE, Grade.EXCELLENT));

        assertTrue(service.isRedDiplomaPossible(gradeBook));
    }
}
