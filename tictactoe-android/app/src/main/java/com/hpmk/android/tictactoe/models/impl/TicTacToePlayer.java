package com.hpmk.android.tictactoe.models.impl;

import com.hpmk.android.tictactoe.models.IPlayTicTacToe;

public class TicTacToePlayer extends Player implements IPlayTicTacToe {

    public TicTacToePlayer(char marker) {
        super(marker);
    }

    @Override
    public int[] move(TicTacToeBoard board, int x, int y) {
        board.set(x, y, getMarker());
        incrementMoveCount();
        return new int[]{x, y};
    }

    @Override
    public int[] move(TicTacToeBoard board, int[] move) {
        return move(board, move[0], move[1]);
    }
}
