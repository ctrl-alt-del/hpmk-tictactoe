package com.hipmunk.android.tictactoe.models;

import com.hipmunk.android.tictactoe.TicTacToeBoard;

public interface IPlayTicTacToe {
    int[] move(TicTacToeBoard board, int x, int y);
    int[] move(TicTacToeBoard board, int[] move);
}
