package nsu.oop.wdianaya.task141.utils;

/**
 * Формы обучения.
 */
public enum StudyForm {
    BUDGET("Бюджет"),
    PAID("Платное обучение");

    private final String title;

    StudyForm(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
