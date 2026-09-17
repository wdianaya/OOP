package org.example.cards;

/**
 * Представляет игральную карту с мастью, достоинством и статусом видимости.
 *
 * @param suit   масть карты
 * @param rank   достоинство карты
 * @param isOpen статус видимости (true — открыта, false — закрыта)
 */
public record Card(Suit suit, Rank rank, boolean isOpen) {
}
