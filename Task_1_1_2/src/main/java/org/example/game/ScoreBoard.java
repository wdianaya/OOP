package org.example.game;

public class ScoreBoard {
    private int gamblerScore = 0;
    private int dealerScore = 0;

    public int getGamblerScore() {
        return gamblerScore;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public void addGamblerScore(int points) {
        this.gamblerScore += points;
    }

    public void addDealerScore(int points) {
        this.dealerScore += points;
    }

    public void incrementGamblerScore() {
        this.gamblerScore++;
    }

    public void incrementDealerScore() {
        this.dealerScore++;
    }

    public void reset() {
        this.gamblerScore = 0;
        this.dealerScore = 0;
    }
}