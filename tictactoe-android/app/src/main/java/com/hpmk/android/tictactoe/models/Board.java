package com.hpmk.android.tictactoe.models;

public abstract class Board {
    private int mCol;
    private int mRow;
    private int mLength;
    private int[][] mBoard;

    public Board(int row, int col) {
        mRow = row;
        mCol = col;
        mLength = row * col;
        mBoard = new int[mRow][mCol];
    }

    public int[][] getBoard() {
        return mBoard;
    }

    public int getRow() {
        return mRow;
    }

    public int getColumn() {
        return mCol;
    }

    public int getLength() {
        return mLength;
    }

    public int get(int row, int col) {
        return mBoard[row][col];
    }

    public Board set(int row, int col, int value) {
        mBoard[row][col] = value;
        return this;
    }

    public boolean isWithinBound(int row, int col) {
        return row >= 0 && row < mRow && col >= 0 && col < mCol;
    }

    public void reset() {
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getColumn(); j++) {
                set(i, j, 0);
            }
        }
    }

    public boolean isCorner(int index) {
        // top left corner || bottom right corner || top right corner || bottom left
        return index == 0 || index == getLength() - 1 || index == getColumn() - 1 || index == getLength() - getColumn();
    }

    public boolean isEdge(int index) {
        // left edge || right edge || top edge || bottom edge
        return index % getColumn() == 0 || (index + 1) % getColumn() == 0 || index < getColumn() || index >= getLength() - getColumn() && index < getLength();
    }

    public boolean hasCenter() {
        return (getLength() - 1) % 2 == 0;
    }

    public boolean isCenter(int index) {
        return hasCenter() && index == (getLength() - 1) / 2;
    }

    public int toIndex(int x, int y) {
        return x * getColumn() + y;
    }

    public int[] toCoordinates(int index) {
        int y = index % getColumn();
        int x = (index - y) / getColumn();
        return new int[]{x, y};
    }

    public abstract boolean isTaken(int row, int col);

    public abstract boolean isAvailable(int x, int y);

    public abstract void print();

}
