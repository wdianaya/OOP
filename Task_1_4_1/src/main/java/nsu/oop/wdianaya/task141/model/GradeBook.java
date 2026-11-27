package nsu.oop.wdianaya.task141.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nsu.oop.wdianaya.task141.service.impl.GradeBookService;

/**
 * Представляет электронную зачетную книжку студента.
 * Хранит в мапе по номеру семестра (ключ) записи об оценках (значения).
 */
public class GradeBook {
    private final Map<Integer, List<GradeRecord>> records;
    private final GradeBookService service = new GradeBookService();

    public GradeBook() {
        this.records = new HashMap<>();
    }

    /**
     * Возвращает все записи об оценках, сгруппированные по номеру семестра.
     */
    public Map<Integer, List<GradeRecord>> getRecord() {
        return this.records;
    }

    /**
     * Добавляет новую запись об оценке в указанный семестр.
     */
    public void addRecord(int semester, GradeRecord gradeRecord) {
        this.records.putIfAbsent(semester, new ArrayList<>());
        this.records.get(semester).add(gradeRecord);
    }

    /**
     * Вычисляет текущий средний балл за все время обучения.
     * Делегирует выполнение методу сервиса.
     *
     * @return средний балл
     */
    public double calculateAverageScore() {
        return service.calculateAverageScore(this);
    }

    /**
     * Проверяет возможность перевода с платной формы обучения на бюджетную.
     * Делегирует выполнение методу сервиса.
     *
     * @return {@code true}, если перевод возможен, иначе {@code false}
     */
    public boolean canTransferToBudget() {
        return service.canTransferToBudget(this);
    }

    /**
     * Проверяет возможность получения повышенной стипендии в указанном семестре.
     * Делегирует выполнение методу сервиса.
     *
     * @param currentSemester номер проверяемого семестра
     * @return {@code true}, если стипендия положена, иначе {@code false}
     */
    public boolean isIncreasedScholarship(int currentSemester) {
        return service.isIncreasedScholarship(this, currentSemester);
    }

    /**
     * Прогнозирует возможность получения красного диплома.
     * Делегирует выполнение методу сервиса.
     *
     * @return {@code true}, если прогноз положительный, иначе {@code false}
     */
    public boolean isRedDiplomaPossible() {
        return service.isRedDiplomaPossible(this);
    }
}
