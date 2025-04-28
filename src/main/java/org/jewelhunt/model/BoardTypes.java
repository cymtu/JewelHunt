package org.jewelhunt.model;

/**
 * Перечисление типов размеров досок в игре, приведены размеры доски и её заполнения камнями
 * @author Барабанов А.В.
 * @version 1.0.1
 */
public enum BoardTypes {
    Small(9, 9, 8, 4, 2, 1, 0, 0, "BoardTypes.Small"),
    Medium(11, 15, 16, 8, 4, 2, 1, 0, "BoardTypes.Medium"),
    Large(13, 26, 32, 16, 8, 4, 2, 1, "BoardTypes.Large");

    private final int lines;
    private final int columns;
    private final int nuggets;
    private final int amethysts;
    private final int chrysolites;
    private final int emeralds;
    private final int sapphires;
    private final int rubies;
    private final String name;

    BoardTypes(int lines, int columns, int nuggets, int amethysts, int chrysolites, int emeralds, int sapphires, int rubies, String name) {
        this.lines = lines;
        this.columns = columns;
        this.nuggets = nuggets;
        this.amethysts = amethysts;
        this.chrysolites = chrysolites;
        this.emeralds = emeralds;
        this.sapphires = sapphires;
        this.rubies = rubies;
        this.name = name;
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }

    public int getNuggets() {
        return nuggets;
    }

    public int getAmethysts() {
        return amethysts;
    }

    public int getChrysolites() {
        return chrysolites;
    }

    public int getEmeralds() {
        return emeralds;
    }

    public int getSapphires() {
        return sapphires;
    }

    public int getRubies() {
        return rubies;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getValueSumAllJewels() {
        int sum;

        sum = nuggets * Jewels.Nugget.getValue()
                + amethysts * Jewels.Amethyst.getValue()
                + chrysolites * Jewels.Chrysolite.getValue()
                + emeralds * Jewels.Emerald.getValue()
                + sapphires * Jewels.Sapphire.getValue()
                + rubies * Jewels.Ruby.getValue();

        return sum;
    }
}
