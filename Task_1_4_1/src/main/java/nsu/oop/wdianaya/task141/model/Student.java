package nsu.oop.wdianaya.task141.model;

import nsu.oop.wdianaya.task141.utils.StudyForm;

/**
 * Хранит инфу о студенте.
 * Фамилия, форма обучения, зачетка.
 *
 */
public class Student {
    private final String fullName;
    private final GradeBook gradeBook;
    private StudyForm studyForm;

    public Student(String fullName, StudyForm studyForm) {
        this.fullName = fullName;
        this.studyForm = studyForm;
        this.gradeBook = new GradeBook();
    }

    public String getFullName() {
        return fullName;
    }

    public StudyForm getStudyForm() {
        return studyForm;
    }

    public GradeBook getGradeBook() {
        return gradeBook;
    }

    public void setStudyForm(StudyForm studyForm) {
        this.studyForm = studyForm;
    }

    @Override
    public String toString() {
        return "Студент: " + fullName + " | Форма обучения: " + studyForm.getTitle();
    }
}
