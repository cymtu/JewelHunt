package org.jewelhunt.ai;

import org.jewelhunt.model.BoardTypes;

import java.util.ArrayList;
import java.util.Random;

public class AiAverage implements IAi{
    private final AiData data;
    private final Random random;
    private int score;

    public AiAverage(BoardTypes boardTypes) {
        this.data = new AiData(boardTypes);
        this.random = new Random();
    }

    public Solution findSolution() {
        calculation();
        return findBestSolution();
    }

    public void calculation() {

        double average = averageValueAllClosedCells();
        for(int c = 0; c < data.getColumns(); c++){
            for(int l = 0; l < data.getLines(); l++){
                if(data.isCellClose(l, c)) {
                    data.set(l, c, average);
                } else {
                    data.set(l, c, 0);
                }
            }
        }

        for(int c = 0; c < data.getColumns(); c++){
            for(int l = 0; l < data.getLines(); l++){
                if(data.isNumberInCell(l, c)) {
                    calculateAverage(l, c);
                }
            }
        }

        for(int c = 0; c < data.getColumns(); c++){
            for(int l = 0; l < data.getLines(); l++){
                if(data.isCellClose(l, c)) {
                    double valueCell = data.get(l, c);
                    int openNumberInCell = getOpenNumberInCell(l, c);
                    if(openNumberInCell > 0) {
                        data.set(l, c, valueCell / openNumberInCell);
                    }
                }
            }
        }
    }

    public AiTypes getType() {
        return AiTypes.Average;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int value) {
        this.score += value;
    }

    public void resetScore() {
        this.score = 0;
    }

    public AiData getData() {
        return data;
    }

    public void init(int[][] CellState, int[][] CellValues) {
        data.init(CellState, CellValues);
    }

    private double averageValueAllClosedCells() {
        return (double) data.sumNotOpenJewels() / data.closedCells();
    }

    private void calculateAverage(int line, int column) {

        int number = data.getValueCell(line, column);
        double numberClosedCells = getNumberClosedCells(line, column);
        double value = number;
        if(numberClosedCells > 0) {
            value = number / numberClosedCells;
        }

        int min;
        // MAX
        for(int i = 0; i < data.getLines(); i++) {
            double valueCell = data.get(i, column);
            if(data.isCellClose(i, column) & valueCell > 0 & value > 0) {
                data.set(i, column, valueCell + value);
            } else {
                data.set(i, column, 0);
            }
        }

        for(int i = 0; i < data.getColumns(); i++) {
            double valueCell = data.get(line, i);
            if(data.isCellClose(line, i) & valueCell > 0 & value > 0) {
                data.set(line, i, valueCell + value);
            } else {
                data.set(line, i, 0);
            }
        }

        min = Math.min(line, column);
        for(int i = 0; (line- min + i) < data.getLines() && (column - min + i) < data.getColumns(); i++) {
            double valueCell = data.get(line - min + i,column - min + i);
            if(data.isCellClose(line - min + i,column - min + i) & valueCell > 0 & value > 0) {
                data.set(line - min + i, column - min + i, valueCell + value);
            } else {
                data.set(line - min + i, column - min + i, 0);
            }
        }

        min = Math.min(data.getLines() - line - 1, column);
        for(int i = 0; (line + min - i) >= 0 && (column - min + i) < data.getColumns(); i++) {
            double valueCell = data.get(line + min - i, column - min + i);
            if(data.isCellClose(line + min - i, column - min + i) & valueCell > 0 & value > 0) {
                data.set(line + min - i, column - min + i, valueCell + value);
            } else {
                data.set(line + min - i, column - min + i, 0);
            }
        }
    }

    private int getNumberClosedCells(int line, int column) {
        int value = 0;

        for(int i = 0; i < data.getLines(); i++) {
            if(data.isCellClose(i, column)) {
                value++;
            }
        }

        for(int i = 0; i < data.getColumns(); i++) {
            if(data.isCellClose(line, i)) {
                value++;
            }
        }

        int min = Math.min(line, column);
        for(int i = 0; (line- min + i) < data.getLines() && (column - min + i) < data.getColumns(); i++) {
            if(data.isCellClose(line - min + i, column - min + i)) {
                value++;
            }
        }

        min = Math.min(data.getLines() - line - 1, column);
        for(int i = 0; (line + min - i)>=0 && (column - min + i) < data.getColumns(); i++) {
            if(data.isCellClose(line + min - i, column - min + i)) {
                value++;
            }
        }

        return value;
    }

    private int getOpenNumberInCell(int line, int column) {
        int value = 0;

        for(int i = 0; i < data.getLines(); i++) {
            if(data.isNumberInCell(i, column)) {
                value++;
            }
        }

        for(int i = 0; i < data.getColumns(); i++) {
            if(data.isNumberInCell(line, i)) {
                value++;
            }
        }

        int min = Math.min(line, column);
        for(int i = 0; (line- min + i) < data.getLines() && (column - min + i) < data.getColumns(); i++) {
            if(data.isNumberInCell(line - min + i, column - min + i)) {
                value++;
            }
        }

        min = Math.min(data.getLines() - line - 1, column);
        for(int i = 0; (line + min - i)>=0 && (column - min + i) < data.getColumns(); i++) {
            if(data.isNumberInCell(line + min - i, column - min + i)) {
                value++;
            }
        }

        return value;
    }

    private Solution findBestSolution() {
        double max = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for(int l = 0; l < data.getLines(); l++){
            for(int c = 0; c < data.getColumns(); c++){
                if(data.get(l, c) > max) {
                    max = data.get(l, c);
                }
            }
        }

        for(int l = 0; l < data.getLines(); l++){
            for(int c = 0; c < data.getColumns(); c++){
                if(data.get(l, c) == max) {
                    list.add(l * data.getColumns() + c);
                }
            }
        }

        int index = random.nextInt(list.size());
        int value = list.get(index);
        int line = value / data.getColumns();
        int column = value % data.getColumns();

        return new Solution(line, column);
    }
}
