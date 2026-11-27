package nsu.oop.wdianaya.task141;

import nsu.oop.wdianaya.task141.model.GradeRecord;
import nsu.oop.wdianaya.task141.model.Student;
import nsu.oop.wdianaya.task141.utils.ControlType;
import nsu.oop.wdianaya.task141.utils.CreditGrade;
import nsu.oop.wdianaya.task141.utils.Grade;
import nsu.oop.wdianaya.task141.utils.StudyForm;

/**
 * Пример работы программы.
 */
public class Main {
    /**
     * Запускаем разные методы для отличника и др.
     */
    public static void main(String[] args) {
        System.out.println("=== Отличник на бюджете ===");
        excellentStudent();

        System.out.println("=== Хороший студент на платке ===");
        goodStudent();
    }

    private static void excellentStudent() {
        Student ivan = new Student("Иванов Иван", StudyForm.BUDGET);

        ivan.getGradeBook().addRecord(1,
                new GradeRecord("Математика", ControlType.EXAM, Grade.EXCELLENT));
        ivan.getGradeBook().addRecord(1,
                new GradeRecord("Физика", ControlType.DIFF_CREDIT, Grade.EXCELLENT));
        ivan.getGradeBook().addRecord(1,
                new GradeRecord("История", ControlType.CREDIT, CreditGrade.PASS));

        ivan.getGradeBook().addRecord(2,
                new GradeRecord("Программирование", ControlType.EXAM, Grade.EXCELLENT));
        ivan.getGradeBook().addRecord(2,
                new GradeRecord("Английский", ControlType.DIFF_CREDIT, Grade.EXCELLENT));

        ivan.getGradeBook().addRecord(8,
                new GradeRecord("Защита ВКР", ControlType.THESIS_DEFENSE, Grade.EXCELLENT));

        System.out.println(ivan);
        System.out.printf("Средний балл: %.2f%n",
                ivan.getGradeBook().calculateAverageScore());
        System.out.println("Повышенная стипендия (семестр 2): "
                + ivan.getGradeBook().isIncreasedScholarship(2));
        System.out.println("Красный диплом: "
                + ivan.getGradeBook().isRedDiplomaPossible());
        System.out.println("Перевод на бюджет: "
                + ivan.getGradeBook().canTransferToBudget() + " (уже на бюджете)");
    }

    private static void goodStudent() {
        Student petr = new Student("Петров Петр", StudyForm.PAID);

        petr.getGradeBook().addRecord(1,
                new GradeRecord("Математика", ControlType.EXAM, Grade.GOOD));
        petr.getGradeBook().addRecord(1,
                new GradeRecord("Физика", ControlType.EXAM, Grade.EXCELLENT));

        petr.getGradeBook().addRecord(2,
                new GradeRecord("Программирование", ControlType.EXAM, Grade.EXCELLENT));
        petr.getGradeBook().addRecord(2,
                new GradeRecord("История", ControlType.DIFF_CREDIT, Grade.SATISFACTORY));

        System.out.println(petr);
        System.out.println("Перевод на бюджет (нет троек за экзамены): "
                + petr.getGradeBook().canTransferToBudget());
        System.out.printf("Средний балл: %.2f%n",
                petr.getGradeBook().calculateAverageScore());
        System.out.println("Красный диплом: "
                + petr.getGradeBook().isRedDiplomaPossible() + " (есть четверки)");
    }
}