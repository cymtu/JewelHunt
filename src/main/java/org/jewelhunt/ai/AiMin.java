package org.jewelhunt.ai;

import java.util.ArrayList;
import java.util.Random;

public class AiMin {
    private AiData data;
    private Random random;

    public AiMin(AiData data) {
        this.data = data;
        this.random = new Random();
    }

    public Solution findSolution() {
        calculation();
        Solution solution = findBestSolution();
        return solution;
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
                    calculateMAX(l, c);
                }
            }
        }

        for(int c = 0; c < data.getColumns(); c++){
            for(int l = 0; l < data.getLines(); l++){
                if(data.isNumberInCell(l, c)) {
                    calculateMIN(l, c);
                }
            }
        }
    }

    private double averageValueAllClosedCells() {
        return (double) data.sumNotOpenJewels() / data.closedCells();
    }

    private void calculateMAX(int line, int column) {

        int number = data.getValueCell(line, column);
        double numberClosedCells = getNumberClosedCells(line, column);
        double value = number;
        if(numberClosedCells > 0) {
            value = number / numberClosedCells;
        }

        int min;
        // MAX
        for(int i = 0; i < data.getLines(); i++) {
            if(data.isCellClose(i, column)) {
                data.set(i, column, Math.max(data.get(i, column), value));
            } else {
                data.set(i, column, 0);
            }
        }

        for(int i = 0; i < data.getColumns(); i++) {
            if(data.isCellClose(line, i)) {
                data.set(line, i, Math.max(data.get(line, i), value));
            } else {
                data.set(line, i, 0);
            }
        }

        min = Math.min(line, column);
        for(int i = 0; (line- min + i) < data.getLines() && (column - min + i) < data.getColumns(); i++) {
            if(data.isCellClose(line - min + i,column - min + i)) {
                data.set(line - min + i, column - min + i, Math.max(data.get(line - min + i,column - min + i), value));
            } else {
                data.set(line - min + i, column - min + i, 0);
            }
        }

        min = Math.min(data.getLines() - line - 1, column);
        for(int i = 0; (line + min - i) >= 0 && (column - min + i) < data.getColumns(); i++) {
            if(data.isCellClose(line + min - i, column - min + i)) {
                data.set(line + min - i, column - min + i, Math.max(data.get(line + min - i, column - min + i), value));
            } else {
                data.set(line + min - i, column - min + i, 0);
            }
        }
    }

    private void calculateMIN(int line, int column) {

        int number = data.getValueCell(line, column);
        double numberClosedCells = getNumberClosedCells(line, column);
        double value = number;
        if(numberClosedCells > 0) {
            value = number / numberClosedCells;
        }

        int min;
        // MIN
        for(int i = 0; i < data.getLines(); i++) {
            if(data.isCellClose(i, column)) {
                data.set(i, column, Math.min(data.get(i, column), value));
            } else {
                data.set(i, column, 0);
            }
        }

        for(int i = 0; i < data.getColumns(); i++) {
            if(data.isCellClose(line, i)) {
                data.set(line, i, Math.min(data.get(line, i), value));
            } else {
                data.set(line, i, 0);
            }
        }

        min = Math.min(line, column);
        for(int i = 0; (line- min + i) < data.getLines() && (column - min + i) < data.getColumns(); i++) {
            if(data.isCellClose(line - min + i, column - min + i)) {
                data.set(line - min + i, column - min + i, Math.min(data.get(line - min + i, column - min + i), value));
            } else {
                data.set(line - min + i, column - min + i, 0);
            }
        }

        min = Math.min(data.getLines() - line - 1, column);
        for(int i = 0; (line + min - i) >= 0 && (column - min + i) < data.getColumns(); i++) {
            if(data.isCellClose(line + min - i, column - min + i)) {
                data.set(line + min - i, column - min + i, Math.min(data.get(line + min - i, column - min + i), value));
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

    public AiTypes getType() {
        return AiTypes.Min;
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
