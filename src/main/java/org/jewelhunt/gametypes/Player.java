package org.jewelhunt.gametypes;

/**
 * Интерфейс для всех игроков
 * @author Барабанов А.В.
 * @version 1.0.1
 */
public interface Player {
    public int getScore();
    public void addScore(int value);
    public void resetScore();
}
