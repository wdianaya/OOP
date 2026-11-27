package nsu.oop.wdianaya.task141.utils;

/**
 * Общий интерфейс для оценок.
 */
public interface AcademicGrade {
    /**
     * Получить название оценки.
     */
    String getTitle();

    /**
     * Получить численное значение оценки.
     */
    int getValue();
}
