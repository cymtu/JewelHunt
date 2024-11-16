package org.jewelhunt.model;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final BoardTypes boardTypes;

    private final Jewels[][] jewels;

    private final boolean[][] isCellOpen;

    private final int[][] numbers;

    private final boolean[][] marks;

    private final Random random;

    private int totalNumberOfMissingJewels;

    public Board(BoardTypes boardTypes){
        this.boardTypes = boardTypes;
        jewels = new Jewels[boardTypes.getLines()][boardTypes.getColumns()];
        isCellOpen = new boolean[boardTypes.getLines()][boardTypes.getColumns()];
        numbers = new int[boardTypes.getLines()][boardTypes.getColumns()];
        marks = new boolean[boardTypes.getLines()][boardTypes.getColumns()];
        random = new Random();
    }

    public void init(){
        reset();
        arrangement();
        sumNumbers();
    }

    public void reset() {
        totalNumberOfMissingJewels = 0;
        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                jewels[l][c] = Jewels.Empty;
                isCellOpen[l][c] = false;
                marks[l][c] = false;
                numbers[l][c] = 0;
            }
        }
    }

    public void sumNumbers() {
        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                if(getJewel(l, c) == Jewels.Empty) {
                    numbers[l][c] = sumJewels(l, c);
                    if(numbers[l][c] == 0 && isCellOpen[l][c]) {
                        markCells(l, c);
                    }
                }
            }
        }
    }

    private void markCells(int line, int column) {
        for(int i = 0; i < boardTypes.getLines(); i++) {
            if(isCellOpen(i, column)) continue;
            setMark(i, column);
        }

        for(int i = 0; i < boardTypes.getColumns(); i++) {
            if(isCellOpen(line, i)) continue;
            setMark(line, i);
        }

        int min = Math.min(line, column);
        for(int i = 0; (line- min + i) < boardTypes.getLines() && (column - min + i) < boardTypes.getColumns(); i++) {
            if(isCellOpen(line - min + i, column - min + i)) continue;
            setMark(line - min + i, column - min + i);
        }

        min = Math.min(boardTypes.getLines() - line - 1, column);
        for(int i = 0; (line + min - i)>=0 && (column - min + i) < boardTypes.getColumns(); i++) {
            if(isCellOpen(line + min - i, column - min + i)) continue;
            setMark(line + min - i, column - min + i);
        }
    }

    private int sumJewels(int line, int column) {
        int sum = 0;
        Jewels jewel;

        for(int i = 0; i < boardTypes.getLines(); i++) {
            if(isCellOpen(i, column)) continue;
            jewel = getJewel(i, column);
            sum += getSumJewel(jewel) ;
        }

        for(int i = 0; i < boardTypes.getColumns(); i++) {
            if(isCellOpen(line, i)) continue;
            jewel = getJewel(line, i);
            sum += getSumJewel(jewel);
        }

        int min = Math.min(line, column);
        for(int i = 0; (line- min + i) < boardTypes.getLines() && (column - min + i) < boardTypes.getColumns(); i++) {
            if(isCellOpen(line - min + i, column - min + i)) continue;
            jewel = getJewel(line - min + i, column - min + i);
            sum += getSumJewel(jewel);
        }

        min = Math.min(boardTypes.getLines() - line - 1, column);
        for(int i = 0; (line + min - i)>=0 && (column - min + i) < boardTypes.getColumns(); i++) {
            if(isCellOpen(line + min - i, column - min + i)) continue;
            jewel = getJewel(line + min - i, column - min + i);
            sum += getSumJewel(jewel) ;
        }

        return sum;
    }

    private int getSumJewel(Jewels jewel) {
        if(jewel == Jewels.Empty) {
            return 0;
        }
        return jewel.getValue();
    }

    private void arrangement(){
        ArrayList<Integer> list = new ArrayList<>();
        int sum = boardTypes.getLines() * boardTypes.getColumns();
        for(int i = 0; i < sum; i++){
            list.add(i);
        }

        arrangementJewels(list, boardTypes.getNuggets(), Jewels.Nugget);
        arrangementJewels(list, boardTypes.getAmethysts(), Jewels.Amethyst);
        arrangementJewels(list, boardTypes.getChrysolites(), Jewels.Chrysolite);
        arrangementJewels(list, boardTypes.getPearls(), Jewels.Pearl);
        arrangementJewels(list, boardTypes.getSapphires(), Jewels.Sapphire);
        arrangementJewels(list, boardTypes.getRubies(), Jewels.Ruby);
    }

    private void arrangementJewels(ArrayList<Integer> list, int count, Jewels jewel) {
        for(int i = 0; i < count; i++){
            int index = random.nextInt(list.size());
            int value = list.get(index);
            int l = value / boardTypes.getColumns();
            int c = value % boardTypes.getColumns();
            list.remove(index);
            jewels[l][c] = jewel;
            totalNumberOfMissingJewels++;
        }
    }

    public boolean isCellOpen(int line, int column){
        return isCellOpen[line][column];
    }

    public void openCell(int line, int column) {
        if(isCellOpen[line][column]) {
            return;
        }

        isCellOpen[line][column] = true;
        if(getJewel(line, column) != Jewels.Empty) {
            totalNumberOfMissingJewels--;
        }
        sumNumbers();
    }

    public void setMark(int line, int column) {
        if(!isCellOpen(line, column)) {
            marks[line][column] = true;
        }
    }

    public void resetMark(int line, int column) {
        if(!isCellOpen(line, column)) {
            marks[line][column] = false;
        }
    }

    public boolean isMark(int line, int column){
        return marks[line][column];
    }

    public Jewels getJewel(int line, int column){
        return jewels[line][column];
    }

    public int getNumber(int line, int column){
        return numbers[line][column];
    }

    public int getTotalNumberOfMissingJewels() {
        return totalNumberOfMissingJewels;
    }

    public BoardTypes getBoardTypes() {
        return boardTypes;
    }

    public int[] getMinMax() {
        int[] result = new int[]{1000, -1000};

        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                if(isCellOpen[l][c] && numbers[l][c] > 0) {
                    result[0] = Math.min(numbers[l][c], result[0]);
                    result[1] = Math.max(numbers[l][c], result[1]);
                }
            }
        }

        return result;
    }
}
