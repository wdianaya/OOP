package nsu.oop.wdianaya.task141.utils;

/**
 * Вид контроля.
 */
public enum ControlType {
    TASK("Задание"),
    TEST("Контрольная"),
    COLLOQUIUM("Коллоквиум"),
    EXAM("Экзамен"),
    DIFF_CREDIT("Дифференцированный зачет"),
    CREDIT("Зачет"),
    PRACTICE_DEFENSE("Защита отчета по практике"),
    THESIS_DEFENSE("Защита ВКР");

    private final String title;

    ControlType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
