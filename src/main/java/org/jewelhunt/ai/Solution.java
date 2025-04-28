package org.jewelhunt.ai;

/**
 * Класс хранит координаты найденного решения
 * @author Барабанов А.В.
 * @version 1.0.1
 */
public class Solution {
    private final int line;
    private final int columns;

    public Solution(int line, int columns) {
        this.line = line;
        this.columns = columns;
    }

    public int getLine() {
        return line;
    }

    public int getColumns() {
        return columns;
    }
}
