package com.hipmunk.android.tictactoe;

import com.hipmunk.android.tictactoe.models.impl.ComputerPlayer;
import com.hipmunk.android.tictactoe.models.impl.HumanPlayer;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeBoard extends Board {

    private HumanPlayer mHumanPlayer;
    private ComputerPlayer mComputerPlayer;

    public TicTacToeBoard(int row, int col, HumanPlayer humanPlayer, ComputerPlayer computerPlayer) {
        super(row, col);
        mHumanPlayer = humanPlayer;
        mComputerPlayer = computerPlayer;
    }

    public HumanPlayer getHumanPlayer() {
        return mHumanPlayer;
    }

    public ComputerPlayer getComputerPlayer() {
        return mComputerPlayer;
    }

    @Override
    public boolean isTaken(int row, int col) {
        return get(row, col) != 0;
    }

    @Override
    public boolean isAvailable(int x, int y) {
        return isWithinBound(x, y) && !isTaken(x, y);
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : getBoard()) {
            for (int item : row) {
                sb.append(item == 0 ? "* " : (char) item + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public List<String> toList() {
        List<String> list = new ArrayList<>(9);
        for (int[] row : getBoard()) {
            for (int item : row) {
                list.add(item == 0 ? "" : String.valueOf((char) item));
            }
        }
        return list;
    }

    public void reset() {
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getColumn(); j++) {
                set(i, j, 0);
            }
        }
    }
}
