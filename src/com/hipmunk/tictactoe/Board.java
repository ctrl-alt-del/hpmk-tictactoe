package com.hipmunk.tictactoe;

public class Board {
    private int mColumn;
    private int mRow;
    private int[][] mBoard;
    private boolean[][] mTaken;

    public Board(int row, int column) {
        mRow = row;
        mColumn = column;
        mBoard = new int[mRow][mColumn];
        mTaken = new boolean[mRow][mColumn];
    }

    public Board set(int row, int column, int value) {
        mBoard[row][column] = value;
        return this;
    }

    public int get(int row, int column) {
        return mBoard[row][column];
    }

    public void setTaken(int row, int column, boolean taken) {
        mTaken[row][column] = taken;
    }

    public boolean isTaken(int row, int column) {
        return mTaken[row][column];
    }

    public boolean isWithinBound(int row, int column) {
        return row >= 0 && row < mRow && column >= 0 && column < mColumn;
    }

    public boolean isAvailable(int x, int y) {
        return isWithinBound(x, y) && !isTaken(x, y);
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : mBoard) {
            for (int item : row) {
                sb.append(item + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public boolean hasWinner(Player player, int[] move) {
        int x = move[0];
        int y = move[1];
        boolean hasWinner = BoardUtils.checkWinner(this, x, y);
        if (hasWinner) {
            BoardUtils.announceWinner(player);
        }
        return hasWinner;
    }
}
