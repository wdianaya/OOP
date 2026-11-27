package nsu.oop.wdianaya.task141.model;


import nsu.oop.wdianaya.task141.utils.AcademicGrade;
import nsu.oop.wdianaya.task141.utils.ControlType;

/**
 * Запись формата: предмет, вид контроля, оценка.
 */
public record GradeRecord(String subject, ControlType type, AcademicGrade grade) {
    @Override
    public String toString() {
        return subject + " (" + type.getTitle() + ") - " + grade.getTitle();
    }
}
