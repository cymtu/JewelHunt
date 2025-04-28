package org.jewelhunt.model;

/**
 * Класс Ячейки игральной доски. Хранить состояние, данные по камню и сумму значений не открытых камней
 * @author Барабанов А.В.
 * @version 1.0.1
 */
public class Cell {
    private Jewels jewels;
    private boolean isOpen;
    private int number;
    private boolean mark;

    public Cell() {
        reset();
    }

    public void reset() {
        this.jewels = Jewels.Empty;
        this.isOpen = false;
        this.number = 0;
        this.mark = false;
    }

    public Jewels getJewels() {
        return jewels;
    }

    public void setJewels(Jewels jewels) {
        this.jewels = jewels;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        isOpen = true;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isMark() {
        return mark;
    }

    public void check() {
        this.mark = true;
    }

    public void unCheck() {
        this.mark = false;
    }
}
