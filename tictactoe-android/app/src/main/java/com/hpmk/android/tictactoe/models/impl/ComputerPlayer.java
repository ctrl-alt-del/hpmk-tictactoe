package com.hpmk.android.tictactoe.models.impl;

import com.hpmk.android.tictactoe.ComputerMoveEvaluation;
import com.hpmk.android.tictactoe.models.IEvaluateTicTacToe;

import java.util.Random;

public class ComputerPlayer extends TicTacToePlayer implements IEvaluateTicTacToe {
    private Random mRandom;

    public ComputerPlayer(char marker) {
        super(marker);
        mRandom = new Random();
    }

    /**
     * logic to get the next move
     */
    @Override
    public int[] evaluateNextMove(TicTacToeBoard board) {

        /*
        * 1. check if there is a spot that makes me win
        * 2. check if there is a spot that blocks other from winning
        */
        // move my winning move
        ComputerMoveEvaluation myOptions = new ComputerMoveEvaluation(board, this, mRandom);
        if (myOptions.hasWinningMove()) {
            return myOptions.getWinningMove();
        }

        // block enemy's winning move
        final HumanPlayer humanPlayer = board.getHumanPlayer();
        ComputerMoveEvaluation enemyOptions = new ComputerMoveEvaluation(board, humanPlayer, mRandom);
        if (enemyOptions.hasWinningMove()) {
            return enemyOptions.getWinningMove();
        }

        return myOptions.getMostValuedAvailableMove();
    }
}
