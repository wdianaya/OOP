package nsu.oop.wdianaya.task141.utils;

public enum CreditGrade implements AcademicGrade{
    PASS("Зачет", 0),
    FAIL("Не зачёт", 0);

    private final String title;
    private final int value;

    CreditGrade(String title, int value) {
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
