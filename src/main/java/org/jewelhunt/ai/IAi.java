package org.jewelhunt.ai;

import org.jewelhunt.gametypes.Player;

/**
 * Интерфейс для всех реализации ИИ
 * @author Барабанов А.В.
 * @version 1.0.1
 */
public interface IAi extends Player {
    Solution findSolution();
    void calculation();
    AiTypes getType();
    public void init(int[][] CellState, int[][] CellValues);
    public AiData getData();
}
