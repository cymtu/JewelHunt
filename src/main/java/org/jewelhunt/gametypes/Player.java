package org.jewelhunt.gametypes;

public class Player implements Score {
    private int score;

    public Player() {
        this.score = 0;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void addScore(int value) {
        this.score += value;
    }

    @Override
    public void resetScore() {
        score = 0;
    }
}
