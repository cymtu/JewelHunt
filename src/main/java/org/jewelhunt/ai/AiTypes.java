package org.jewelhunt.ai;

public enum AiTypes {
    MaxMin("AiTypes.MaxMin");

    private final String name;

    AiTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
