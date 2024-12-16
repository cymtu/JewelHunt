package org.jewelhunt.model;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private static int CELL_CLOSED = -1;
    private static int CELL_CONTAINS_JEWEL = -2;
    private final BoardTypes boardTypes;
    private final Cell[][] cells;
    private final Random random;
    private int totalNumberOfMissingJewels;

    public Board(BoardTypes boardTypes){
        this.boardTypes = boardTypes;
        cells = new Cell[boardTypes.getLines()][boardTypes.getColumns()];
        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                cells[l][c] = new Cell();
            }
        }
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
                cells[l][c].reset();
            }
        }
    }

    public void sumNumbers() {
        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                if(getJewel(l, c) == Jewels.Empty) {
                    Cell cell = cells[l][c];
                    cell.setNumber(sumJewels(l, c));
                    if(cell.getNumber() == 0 && cell.isOpen()) {
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
            cells[l][c].setJewels(jewel);
            totalNumberOfMissingJewels++;
        }
    }

    public boolean isCellOpen(int line, int column){
        return cells[line][column].isOpen();
    }

    public void openCell(int line, int column) {
        if(cells[line][column].isOpen()) {
            return;
        }

        cells[line][column].open();
        if(getJewel(line, column) != Jewels.Empty) {
            totalNumberOfMissingJewels--;
        }
        sumNumbers();
    }

    public void setMark(int line, int column) {
        if(!isCellOpen(line, column)) {
            cells[line][column].check();
        }
    }

    public void resetMark(int line, int column) {
        if(!isCellOpen(line, column)) {
            cells[line][column].unCheck();
        }
    }

    public boolean isMark(int line, int column){
        return cells[line][column].isMark();
    }

    public Jewels getJewel(int line, int column){
        return cells[line][column].getJewels();
    }

    public boolean isEmpty(int line, int column) {
        return getJewel(line, column) == Jewels.Empty;
    }

    public int getNumber(int line, int column){
        return cells[line][column].getNumber();
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
                Cell cell = cells[l][c];
                if(cell.isOpen() && cell.getNumber() > 0) {
                    result[0] = Math.min(cell.getNumber(), result[0]);
                    result[1] = Math.max(cell.getNumber(), result[1]);
                }
            }
        }

        return result;
    }

    public int[][] getVisiblePartOfBoard() {
        int[][] visible = new int[boardTypes.getLines()][boardTypes.getColumns()];
        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                if(isCellOpen(l, c)) {
                    if(getJewel(l, c) == Jewels.Empty) {
                        visible[l][c] = getNumber(l, c);  // значение суммы драгоценностей на всех пересечениях
                    } else {
                        visible[l][c] = CELL_CONTAINS_JEWEL; // в ячейке драгоценный камень
                    }
                } else {
                    visible[l][c] = CELL_CLOSED; // ячейка закрыта
                }
            }
        }

        return visible;
    }

    public int sumNotOpenJewels() {
        int sumJewels = 0;

        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                if(!isCellOpen(l, c)) {
                    if(getJewel(l, c) != Jewels.Empty) {
                        sumJewels += getJewel(l, c).getValue();
                    }
                }
            }
        }
        return sumJewels;
    }

    public int closedCells() {
        int closedCells = 0;

        for(int c = 0; c < boardTypes.getColumns(); c++){
            for(int l = 0; l < boardTypes.getLines(); l++){
                if(!isCellOpen(l, c)) {
                    closedCells += 1;
                }
            }
        }
        return closedCells;
    }
}
