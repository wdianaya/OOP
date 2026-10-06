package ru.nsu.oop.parser;

/**
 * Класс, реализующий отправку сообщения на исключение
 * при ошибке с определением токена.
 *
 */
public class ParseException extends RuntimeException {
    public ParseException(String message) {
        super(message);
    }
}
