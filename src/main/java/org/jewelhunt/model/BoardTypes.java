package org.jewelhunt.model;

public enum BoardTypes {
    Small(9, 9, 8, 4, 2, 1, 0, 0, "BoardTypes.Small"),
    Medium(11, 15, 16, 8, 4, 2, 1, 0, "BoardTypes.Medium"),
    Large(13, 26, 32, 16, 8, 4, 2, 1, "BoardTypes.Large");

    private final int lines;
    private final int columns;
    private final int nuggets;
    private final int amethysts;
    private final int chrysolites;
    private final int pearls;
    private final int sapphires;
    private final int rubies;
    private String name;

    BoardTypes(int lines, int columns, int nuggets, int amethysts, int chrysolites, int pearls, int sapphires, int rubies, String name) {
        this.lines = lines;
        this.columns = columns;
        this.nuggets = nuggets;
        this.amethysts = amethysts;
        this.chrysolites = chrysolites;
        this.pearls = pearls;
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

    public int getPearls() {
        return pearls;
    }

    public int getSapphires() {
        return sapphires;
    }

    public int getRubies() {
        return rubies;
    }

    public double originalValue() {
        double value;
        value = nuggets * Jewels.Nugget.getValue()
                + amethysts * Jewels.Amethyst.getValue()
                + chrysolites * Jewels.Chrysolite.getValue()
                + pearls * Jewels.Pearl.getValue()
                + sapphires * Jewels.Sapphire.getValue()
                + rubies * Jewels.Ruby.getValue();
        value = value / (lines * columns);
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
