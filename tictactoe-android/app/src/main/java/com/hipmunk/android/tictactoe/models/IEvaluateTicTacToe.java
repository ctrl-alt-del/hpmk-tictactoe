package com.hipmunk.android.tictactoe.models;

import com.hipmunk.android.tictactoe.Board;

public interface IEvaluateTicTacToe {
    int[] evaluateNextMove(Board board);
}
