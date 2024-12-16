package org.jewelhunt.ai;

import java.util.ArrayList;
import java.util.Random;

public class AIMinMax {

    Random random;

    public AIMinMax() {
        random = new Random();
    }

    public int[] move(int[][] visible, int sumNotOpenJewels, int closedCells) {
        double averageValue = ((double) sumNotOpenJewels / closedCells);
        double[][] calculation = getCalculation(visible, averageValue);
        return findBestSolution(calculation);
    }

    public double[][] getCalculation(int[][] visible, double originalValue) {
        int lines = visible.length;
        int columns = visible[0].length;
        double[][] calculation = new double[lines][columns];

        for(int c = 0; c < columns; c++){
            for(int l = 0; l < lines; l++){
                if(visible[l][c] == - 1) {
                    calculation[l][c] = originalValue;
                } else {
                    calculation[l][c] = 0;
                }
            }
        }

        for(int c = 0; c < columns; c++){
            for(int l = 0; l < lines; l++){
                if(visible[l][c] >= 0) {
                    calculateMAX(calculation, visible, l, c);
                }
            }
        }

        for(int c = 0; c < columns; c++){
            for(int l = 0; l < lines; l++){
                if(visible[l][c] >= 0) {
                    calculateMIN(calculation, visible, l, c);
                }
            }
        }

        return calculation;
    }

    private int[] findBestSolution(double[][] calculation) {
        int[] move = new int[2];
        double max = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for(int l = 0; l < calculation.length; l++){
            for(int c = 0; c < calculation[l].length; c++){
                if(calculation[l][c] > max) {
                    max = calculation[l][c];
                }
            }
        }

        for(int l = 0; l < calculation.length; l++){
            for(int c = 0; c < calculation[l].length; c++){
                if(calculation[l][c] == max) {
                    list.add(l * calculation[l].length + c);
                }
            }
        }

        int index = random.nextInt(list.size());
        int value = list.get(index);
        move[0] = value / calculation[0].length;
        move[1] = value % calculation[0].length;

        return move;
    }
    private void calculateMAX(double[][] calculation, int[][] visible, int line, int column) {

        int number = visible[line][column];
        double numberClosedCells = getNumberClosedCells(visible, line, column);
        double value = number;
        if(numberClosedCells > 0) {
            value = number / numberClosedCells;
        }

        int min;
        // MAX
        for(int i = 0; i < visible.length; i++) {
            if(visible[i][column] == - 1) {
                calculation[i][column] = Math.max(calculation[i][column], value);
            } else {
                calculation[i][column] = 0;
            }
        }

        for(int i = 0; i < visible[0].length; i++) {
            if(visible[line][i] == -1) {
                calculation[line][i] = Math.max(calculation[line][i], value);
            } else {
                calculation[line][i] = 0;
            }
        }

        min = Math.min(line, column);
        for(int i = 0; (line- min + i) < visible.length && (column - min + i) < visible[0].length; i++) {
            if(visible[line - min + i][column - min + i] == -1) {
                calculation[line - min + i][column - min + i] = Math.max(calculation[line - min + i][column - min + i], value);
            } else {
                calculation[line - min + i][column - min + i] = 0;
            }
        }

        min = Math.min(visible.length - line - 1, column);
        for(int i = 0; (line + min - i) >= 0 && (column - min + i) < visible[0].length; i++) {
            if(visible[line + min - i][column - min + i] == -1) {
                calculation[line + min - i][column - min + i] = Math.max(calculation[line + min - i][column - min + i], value);
            } else {
                calculation[line + min - i][column - min + i] = 0;
            }
        }
    }

    private void calculateMIN(double[][] calculation, int[][] visible, int line, int column) {

        int number = visible[line][column];
        double numberClosedCells = getNumberClosedCells(visible, line, column);
        double value = number;
        if(numberClosedCells > 0) {
            value = number / numberClosedCells;
        }

        int min;
        // MIN
        for(int i = 0; i < visible.length; i++) {
            if(visible[i][column] == - 1) {
                calculation[i][column] = Math.min(calculation[i][column], value);
            } else {
                calculation[i][column] = 0;
            }
        }

        for(int i = 0; i < visible[0].length; i++) {
            if(visible[line][i] == -1) {
                calculation[line][i] = Math.min(calculation[line][i], value);
            } else {
                calculation[line][i] = 0;
            }
        }

        min = Math.min(line, column);
        for(int i = 0; (line- min + i) < visible.length && (column - min + i) < visible[0].length; i++) {
            if(visible[line - min + i][column - min + i] == -1) {
                calculation[line - min + i][column - min + i] = Math.min(calculation[line - min + i][column - min + i], value);
            } else {
                calculation[line - min + i][column - min + i] = 0;
            }
        }

        min = Math.min(visible.length - line - 1, column);
        for(int i = 0; (line + min - i) >= 0 && (column - min + i) < visible[0].length; i++) {
            if(visible[line + min - i][column - min + i] == -1) {
                calculation[line + min - i][column - min + i] = Math.min(calculation[line + min - i][column - min + i], value);
            } else {
                calculation[line + min - i][column - min + i] = 0;
            }
        }
    }

    private int getNumberClosedCells(int[][] visible, int line, int column) {
        int value = 0;

        for(int i = 0; i < visible.length; i++) {
            if(visible[i][column] == - 1) {
                value++;
            }
        }

        for(int i = 0; i < visible[0].length; i++) {
            if(visible[line][i] == -1) {
                value++;
            }
        }

        int min = Math.min(line, column);
        for(int i = 0; (line- min + i) < visible.length && (column - min + i) < visible[0].length; i++) {
            if(visible[line - min + i][column - min + i] == -1) {
                value++;
            }
        }

        min = Math.min(visible.length - line - 1, column);
        for(int i = 0; (line + min - i)>=0 && (column - min + i) < visible[0].length; i++) {
            if(visible[line + min - i][column - min + i] == -1) {
                value++;
            }
        }

        return value;
    }

    public AiTypes getType() {
        return AiTypes.MaxMin;
    }
}
