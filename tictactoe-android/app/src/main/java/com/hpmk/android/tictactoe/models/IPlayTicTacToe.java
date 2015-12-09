package com.hpmk.android.tictactoe.models;

import com.hpmk.android.tictactoe.models.impl.TicTacToeBoard;

public interface IPlayTicTacToe {
    int[] move(TicTacToeBoard board, int x, int y);
    int[] move(TicTacToeBoard board, int[] move);
}
