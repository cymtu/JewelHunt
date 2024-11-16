package org.jewelhunt.model;

public enum GameTypes {
    Single("GameTypes.Single"),
    PlayWithAI("GameTypes.PlayWithAI");

    private String name;

    GameTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
