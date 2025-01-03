package org.jewelhunt.model;

public enum Jewels {
    Empty(0, "Empty"),
    Nugget(1, "Nugget"),
    Amethyst(2, "Amethyst"),
    Chrysolite(4, "Chrysolite"),
    Emerald(8, "Emerald"),
    Sapphire(16, "Sapphire"),
    Ruby(32, "Ruby");

    private final int value;
    private final String name;

    Jewels(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
