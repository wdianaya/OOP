package org.example;

public class Dealer extends Player{
    public boolean shouldTakeCard() {
        return getFullSum() < 17;
    }

    @Override
    public void takeCard(Rank rank, Suit suit, boolean isOpen) {
        if (shouldTakeCard())
            super.takeCard(rank, suit, isOpen);
    }
}
