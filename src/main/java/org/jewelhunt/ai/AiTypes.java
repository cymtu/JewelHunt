package org.jewelhunt.ai;

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

    public static IAi ai(AiTypes aiTypes, AiData data) {
        IAi ai;

        if (Objects.requireNonNull(aiTypes) == AiTypes.Average) {
            ai = new AiAverage(data);
        } else {
            ai = new AiMin(data);
        }

        return ai;
    }

}
