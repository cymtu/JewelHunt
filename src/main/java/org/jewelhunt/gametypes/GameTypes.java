package org.jewelhunt.gametypes;

import org.jewelhunt.controllers.Controller;

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

    public static ServiceGame getService(Controller controller) {
        ServiceGame service;

        if (controller.getGameTypes() == Single) {
            service = new ServiceSingle(controller);
        } else if (controller.getGameTypes() == PlayWithAI) {
            service = new ServicePlayWithAI(controller);
        } else {
            service = new ServiceGameOfArtificialOpponents(controller);
        }

        return service;
    }
}
