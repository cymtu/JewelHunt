package org.jewelhunt.gametypes;

/**
 * Перечисление типов игры
 * @author Барабанов А.В.
 * @version 1.0.1
 */
public enum GameTypes {
    Single("GameTypes.Single"),
    PlayWithAI("GameTypes.PlayWithAI"),
    GameOfArtificialOpponents("GameTypes.GameOfArtificialOpponents");

    private final String name;

    GameTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
