package org.jewelhunt.gametypes;

import javafx.scene.input.MouseButton;
import org.jewelhunt.ai.IAi;

public interface ServiceGame {
    public void mouseClicked(int line, int column, MouseButton button);
    public int getScorePlayer();
    public void resetScore();
    public int getNumberAiGames();
    public void setNumberAiGames(int numberAiGames);
    public int getScoreAi();
    public int getScoreAiSecond();
    public IAi getAiOpponent();
    public IAi getAiSecondOpponent();
    public void setAiOpponent(IAi aiOpponent);
    public void setAiSecondOpponent(IAi aiSecondOpponent);
}
