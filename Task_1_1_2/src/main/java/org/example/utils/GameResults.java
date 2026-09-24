package org.example.utils;

/**
 * Перечисление, определяющее возможные исходы или статусы
 * текущего этапа игры в блэкджек (победа, проигрыш, продолжение).
 */
public enum GameResults {
    WIN, // if score == 21
    FAIL, // if score > 21
    CONT; // if 0 <= score < 21

    /**
     * Выводит промежуточную оценку текущей суммы очков.
     */
    public static GameResults checkedRes(int score) {
        if (score == 21) {
            return GameResults.WIN;
        }
        if (score > 21) {
            return GameResults.FAIL;
        }
        return GameResults.CONT;
    }
}
