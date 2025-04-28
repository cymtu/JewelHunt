package org.jewelhunt.model;

/**
 * Перечисление драгоценных камней, используемых в игре
 * @author Барабанов А.В.
 * @version 1.0.1
 */
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

    /**
     * Возращает ценность камня
     * @return Значение ценности
     */
    public int getValue() {
        return value;
    }

    /**
     * Возвращает наименование камня
     * @return Наименование камня
     */
    public String getName() {
        return name;
    }
}
