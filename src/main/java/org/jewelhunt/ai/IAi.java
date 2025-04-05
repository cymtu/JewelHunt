package org.jewelhunt.ai;

import org.jewelhunt.gametypes.Score;

public interface IAi extends Score {
    Solution findSolution();
    void calculation();
    AiTypes getType();
    public void init(int[][] CellState, int[][] CellValues);
    public AiData getData();
}
