package org.jewelhunt.ai;

import org.jewelhunt.model.BoardTypes;

import java.util.Objects;

public enum AiTypes {
    Min("AiTypes.Min"),
    Average("AiType.Average");

    private final String name;

    AiTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static IAi ai(AiTypes aiTypes, BoardTypes boardTypes) {
        IAi ai;

        if (Objects.requireNonNull(aiTypes) == AiTypes.Average) {
            ai = new AiAverage(boardTypes);
        } else {
            ai = new AiMin(boardTypes);
        }

        return ai;
    }

}
