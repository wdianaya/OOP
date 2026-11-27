package nsu.oop.wdianaya.task141.model;

import nsu.oop.wdianaya.task141.utils.ControlType;
import nsu.oop.wdianaya.task141.utils.Grade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GradeBookTest {
    @Test
    void addRecord_NewSemester_CreatesListAndAdds() {
        GradeBook gradeBook = new GradeBook();
        GradeRecord record = new GradeRecord(
                "Математика", ControlType.EXAM, Grade.EXCELLENT);

        gradeBook.addRecord(1, record);

        assertNotNull(gradeBook.getRecord().get(1));
        assertEquals(1, gradeBook.getRecord().get(1).size());
        assertEquals(record, gradeBook.getRecord().get(1).get(0));
    }

    @Test
    void addRecord_ExistingSemester_AddsToList() {
        GradeBook gradeBook = new GradeBook();
        gradeBook.addRecord(1,
                new GradeRecord("Математика", ControlType.EXAM, Grade.EXCELLENT));
        gradeBook.addRecord(1,
                new GradeRecord("Физика", ControlType.EXAM, Grade.GOOD));

        assertEquals(2, gradeBook.getRecord().get(1).size());
    }

    @Test
    void delegatedMethods_WorkCorrectly() {
        GradeBook gradeBook = new GradeBook();
        gradeBook.addRecord(1,
                new GradeRecord("Математика", ControlType.EXAM, Grade.EXCELLENT));

        assertEquals(5.0, gradeBook.calculateAverageScore());
        assertTrue(gradeBook.isIncreasedScholarship(1));
    }
}