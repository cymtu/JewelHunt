package org.jewelhunt.model;

public enum Jewels {
    Empty(0),
    Nugget(1),
    Amethyst(2),
    Chrysolite(4),
    Pearl(8),
    Sapphire(16),
    Ruby(32);

    private final int value;

    Jewels(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
