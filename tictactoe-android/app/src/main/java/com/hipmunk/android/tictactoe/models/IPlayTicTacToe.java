package com.hipmunk.android.tictactoe.models;

import com.hipmunk.android.tictactoe.Board;

public interface IPlayTicTacToe {
    int[] move(Board board, int x, int y);
    int[] move(Board board, int[] move);
}
