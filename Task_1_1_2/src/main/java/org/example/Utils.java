package org.example;

/**
 * Перечисление, определяющее возможные исходы или статусы
 * текущего этапа игры в блэкджек (победа, проигрыш, продолжение).
 */
public enum Utils {
    WIN, // if score == 21
    FAIL, // if score > 21
    CONT; // if 0 <= score < 21
}
