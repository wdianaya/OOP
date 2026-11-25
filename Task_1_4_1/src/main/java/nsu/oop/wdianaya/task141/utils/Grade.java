package nsu.oop.wdianaya.task141.utils;

/**
 * Всевозмодные виды оценок.
 */
public enum Grade {
    EXCELLENT("Отлично", 5),
    GOOD("Хорошо", 4),
    SATISFACTORY("Удовлетворительно", 3),
    UNSATISFACTORY("Неудовлетворительно", 2),
    PASS("Зачет", 0);

    private final String title;
    private final int value;

    Grade(String title, int value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public int getValue() {
        return value;
    }

}
