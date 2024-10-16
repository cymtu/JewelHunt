package org.jewelhunt.model;

import java.util.ArrayList;
import java.util.Random;

public class AI {

    Random random;

    public AI() {
        random = new Random();
    }

    public int[] move(Board board) {
        int[][] visible = getVisible(board);
        double[][] calculation = getCalculation(visible, board);
        return findBestSolution(calculation);
    }

    private int[][] getVisible(Board board) {
        BoardTypes boardTypes = board.getBoardTypes();
        int[][] visible = new int[boardTypes.getLines()][boardTypes.getColumns()];
        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                if(board.isCellOpen(l, c)) {
                    if(board.getJewel(l, c) == Jewels.Empty) {
                        visible[l][c] = board.getNumber(l, c);  // значение суммы драгоценностей на всех пересечениях
                    } else {
                        visible[l][c] = - 2; // в ячейке драгоценный камень
                    }
                } else {
                    visible[l][c] = - 1; // ячейка закрыта
                }
            }
        }

        return visible;
    }

    private double[][] getCalculation(int[][] visible, Board board) {
        BoardTypes boardTypes = board.getBoardTypes();
        double[][] calculation = new double[boardTypes.getLines()][boardTypes.getColumns()];

        double originalValue = originalValue(visible, board);
        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                if(visible[l][c] == - 1) {
                    calculation[l][c] = originalValue;
                } else {
                    calculation[l][c] = 0;
                }
            }
        }

        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                if(visible[l][c] >= 0) {
                    calculate(calculation, visible, l, c);
                }
            }
        }

        return calculation;
    }

    private double originalValue(int[][] visible, Board board) {
        double closedCells = 0;
        double sumJewels = 0;

        BoardTypes boardTypes = board.getBoardTypes();
        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                if(visible[l][c] == -1) {
                    closedCells += 1;
                    if(board.getJewel(l, c) != Jewels.Empty) {
                        sumJewels += board.getJewel(l, c).getValue();
                    }
                }
            }
        }
        return sumJewels/closedCells;
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
                    list.add(l * calculation.length + c);
                }
            }
        }

        int index = random.nextInt(list.size());
        int value = list.get(index);
        move[0] = value / calculation.length;
        move[1] = value % calculation.length;

        return move;
    }
    private void calculate(double[][] calculation, int[][] visible, int line, int column) {

        int number = visible[line][column];
        double numberClosedCells = getNumberClosedCells(visible, line, column);
        double value = number;
        if(numberClosedCells > 0) {
            value = number / numberClosedCells;
        }

        for(int i = 0; i < visible.length; i++) {
            if(visible[i][column] == - 1) {
                calculation[i][column] += value;
            } else {
                calculation[i][column] = 0;
            }
        }

        for(int i = 0; i < visible[0].length; i++) {
            if(visible[line][i] == -1) {
                calculation[line][i] += value;
            } else {
                calculation[line][i] = 0;
            }
        }

        int min = Math.min(line, column);
        for(int i = 0; (line- min + i) < visible.length && (column - min + i) < visible[0].length; i++) {
            if(visible[line - min + i][column - min + i] == -1) {
                calculation[line - min + i][column - min + i] += value;
            } else {
                calculation[line - min + i][column - min + i] = 0;
            }
        }

        min = Math.min(visible.length - line - 1, column);
        for(int i = 0; (line + min - i) >= 0 && (column - min + i) < visible[0].length; i++) {
            if(visible[line + min - i][column - min + i] == -1) {
                calculation[line + min - i][column - min + i] += value;
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
}
