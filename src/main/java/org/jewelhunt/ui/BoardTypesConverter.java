package org.jewelhunt.ui;

import javafx.util.StringConverter;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.model.BoardTypes;

public class BoardTypesConverter extends StringConverter<BoardTypes> {

    private final Controller controller;

    public BoardTypesConverter(Controller controller) {
        this.controller = controller;
    }
    @Override
    public String toString(BoardTypes boardTypes) {
        return controller.getMessage(boardTypes.toString());
    }

    @Override
    public BoardTypes fromString(String s) {
        return null;
    }
}
