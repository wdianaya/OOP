package org.example.utils;

/**
 * Перечисление, определяющее возможные исходы или статусы
 * текущего этапа игры в блэкджек (победа, проигрыш, продолжение).
 */
public enum Utils {
    WIN, // if score == 21
    FAIL, // if score > 21
    CONT; // if 0 <= score < 21

    /**
     * Выводит промежуточную оценку текущей суммы очков.
     */
    public static Utils checkedRes(int score) {
        if (score == 21) {
            return Utils.WIN;
        }
        if (score > 21) {
            return Utils.FAIL;
        }
        return Utils.CONT;
    }
}
