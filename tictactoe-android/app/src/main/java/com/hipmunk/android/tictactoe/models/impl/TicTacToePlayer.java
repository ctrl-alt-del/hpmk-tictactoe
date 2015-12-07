package com.hipmunk.android.tictactoe.models.impl;

import com.hipmunk.android.tictactoe.Board;
import com.hipmunk.android.tictactoe.models.IPlayTicTacToe;

public class TicTacToePlayer extends Player implements IPlayTicTacToe {

    public TicTacToePlayer(char marker) {
        super(marker);
    }

    @Override
    public int[] move(Board board, int x, int y) {
        board.set(x, y, getMarker());
        incrementMoveCount();
        return new int[]{x, y};
    }

    @Override
    public int[] move(Board board, int[] move) {
        return move(board, move[0], move[1]);
    }
}
