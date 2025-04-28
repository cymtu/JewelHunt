package org.jewelhunt.gametypes;

/**
 * Реализация интерфейса Игрока для Человека
 * @author Барабанов А.В.
 * @version 1.0.1
 */
public class Human implements Player {
    private int score;

    public Human() {
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
