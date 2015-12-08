package com.hipmunk.android.tictactoe;

import com.hipmunk.android.tictactoe.models.impl.ComputerPlayer;
import com.hipmunk.android.tictactoe.models.impl.HumanPlayer;
import com.hipmunk.android.tictactoe.models.impl.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int mCol;
    private int mRow;
    private int[][] mBoard;
    private HumanPlayer mHumanPlayer;
    private ComputerPlayer mComputerPlayer;

    public Board(int row, int col, HumanPlayer humanPlayer, ComputerPlayer computerPlayer) {
        mRow = row;
        mCol = col;
        mHumanPlayer = humanPlayer;
        mComputerPlayer = computerPlayer;
        mBoard = new int[mRow][mCol];
    }

    public int getRow() {
        return mRow;
    }

    public int getColumn() {
        return mCol;
    }

    public HumanPlayer getHumanPlayer() {
        return mHumanPlayer;
    }

    public ComputerPlayer getComputerPlayer() {
        return mComputerPlayer;
    }

    public Board set(int row, int col, int value) {
        mBoard[row][col] = value;
        return this;
    }

    public int get(int row, int col) {
        return mBoard[row][col];
    }

    public boolean isTaken(int row, int col) {
        return mBoard[row][col] != 0;
    }

    public boolean isWithinBound(int row, int col) {
        return row >= 0 && row < mRow && col >= 0 && col < mCol;
    }

    public boolean isAvailable(int x, int y) {
        return isWithinBound(x, y) && !isTaken(x, y);
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : mBoard) {
            for (int item : row) {
                sb.append(item == 0 ? "* " : (char) item + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public List<String> toList() {
        List<String> list = new ArrayList<>(9);
        for (int[] row : mBoard) {
            for (int item : row) {
                list.add(item == 0 ? "*" : String.valueOf((char) item));
            }
        }
        return list;
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

    public boolean isGameEnd() {
        for (int[] row : mBoard) {
            for (int item : row) {
                if (item == 0) return false;
            }
        }
        return true;
    }

    public void reset() {
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getColumn(); j++) {
                set(i, j, 0);
            }
        }
    }
}
