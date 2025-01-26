package org.jewelhunt.ai;

import org.jewelhunt.model.BoardTypes;

public class AiData {

    private static final int CELL_CLOSED = -1;
    private static final int CELL_CONTAINS_NUMBER = 0;
    private static final int CELL_CONTAINS_JEWEL = 1;
    private int[][] cellState;
    private int[][] cellValues;
    private final double[][] calculation;
    private final BoardTypes boardTypes;

    public AiData(BoardTypes boardTypes) {
        this.boardTypes = boardTypes;
        calculation = new double[boardTypes.getLines()][boardTypes.getColumns()];
    }

    public void init(int[][] cellState, int[][] cellValues) {
        this.cellState = cellState;
        this.cellValues = cellValues;
    }

    public int getLines() {
        return boardTypes.getLines();
    }

    public int getColumns() {
        return boardTypes.getColumns();
    }

    public int getValueSumAllJewels() {
        return boardTypes.getValueSumAllJewels();
    }

    public boolean isCellClose(int line, int column){
        return cellState[line][column] == CELL_CLOSED;
    }

    public boolean isNumberInCell(int line, int column){
        return cellState[line][column] == CELL_CONTAINS_NUMBER;
    }

    public boolean isJewelInCell(int line, int column){
        return cellState[line][column] == CELL_CONTAINS_JEWEL;
    }

    public int getValueCell(int line, int column) {
        return cellValues[line][column];
    }

    public int sumNotOpenJewels() {
        int sumJewels = getValueSumAllJewels();

        for(int c = 0; c < getColumns(); c++) {
            for(int l = 0; l < getLines(); l++){
                if(isJewelInCell(l, c)) {
                    sumJewels -= getValueCell(l, c);
                }
            }
        }
        return sumJewels;
    }

    public int closedCells() {
        int closedCells = 0;

        for(int c = 0; c < getColumns(); c++){
            for(int l = 0; l < getLines(); l++){
                if(isCellClose(l, c)) {
                    closedCells += 1;
                }
            }
        }
        return closedCells;
    }

    public double get(int line, int column) {
        return calculation[line][column];
    }

    public void set(int line, int column, double value) {
        calculation[line][column] = value;
    }
}
