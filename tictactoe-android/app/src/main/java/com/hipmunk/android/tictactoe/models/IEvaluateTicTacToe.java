package com.hipmunk.android.tictactoe.models;

import com.hipmunk.android.tictactoe.TicTacToeBoard;

public interface IEvaluateTicTacToe {
    int[] evaluateNextMove(TicTacToeBoard board);
}
