package org.jewelhunt.ui;

import javafx.util.StringConverter;
import org.jewelhunt.ai.AiTypes;
import org.jewelhunt.controllers.Controller;

public class AiTypesConverter extends StringConverter<AiTypes> {

    private final Controller controller;

    public AiTypesConverter(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String toString(AiTypes aiTypes) {
        return controller.getMessage(aiTypes.toString());
    }

    @Override
    public AiTypes fromString(String s) {
        return null;
    }
}
