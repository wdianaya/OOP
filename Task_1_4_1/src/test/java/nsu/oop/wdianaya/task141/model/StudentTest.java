package nsu.oop.wdianaya.task141.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudentTest {
    @Test
    void studentInitialization_Works() {
        Student student = new Student("Иван", nsu.oop.wdianaya.task141.utils.StudyForm.BUDGET);
        assertEquals("Иван", student.getFullName());
        assertNotNull(student.getGradeBook());
        assertEquals(nsu.oop.wdianaya.task141.utils.StudyForm.BUDGET, student.getStudyForm());

        student.setStudyForm(nsu.oop.wdianaya.task141.utils.StudyForm.PAID);
        assertEquals(nsu.oop.wdianaya.task141.utils.StudyForm.PAID, student.getStudyForm());
    }
}