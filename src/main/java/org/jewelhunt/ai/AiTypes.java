package org.jewelhunt.ai;

public enum AiTypes {
    Min("AiTypes.Min");

    private final String name;

    AiTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
