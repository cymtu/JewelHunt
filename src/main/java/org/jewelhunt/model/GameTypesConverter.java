package org.jewelhunt.model;

import javafx.util.StringConverter;
import org.jewelhunt.controllers.Controller;

public class GameTypesConverter extends StringConverter<GameTypes> {

    private final Controller controller;

    public GameTypesConverter(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String toString(GameTypes gameTypes) {
        return controller.getMessage(gameTypes.toString());
    }

    @Override
    public GameTypes fromString(String s) {
        return null;
    }
}
