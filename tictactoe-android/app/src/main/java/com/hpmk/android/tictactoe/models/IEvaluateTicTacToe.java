package com.hpmk.android.tictactoe.models;

import com.hpmk.android.tictactoe.models.impl.TicTacToeBoard;

public interface IEvaluateTicTacToe {
    int[] evaluateNextMove(TicTacToeBoard board);
}
