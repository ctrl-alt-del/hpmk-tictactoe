package com.hpmk.android.tictactoe.models;

public abstract class Board {
    private int mCol;
    private int mRow;
    private int[][] mBoard;

    public Board(int row, int col) {
        mRow = row;
        mCol = col;
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

    public abstract boolean isTaken(int row, int col);

    public abstract boolean isAvailable(int x, int y);

    public abstract void print();
}
