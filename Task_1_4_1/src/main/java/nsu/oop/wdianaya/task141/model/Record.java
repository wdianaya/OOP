package nsu.oop.wdianaya.task141.model;


import nsu.oop.wdianaya.task141.utils.ControlType;
import nsu.oop.wdianaya.task141.utils.Grade;

/**
 * Запись формата: предмет, вид контроля, оценка.
 */
public record Record(String subject, ControlType type, Grade grade) {
    @Override
    public String toString() {
        return subject + " (" + type.getTitle() + ") - " + grade.getTitle();
    }
}
