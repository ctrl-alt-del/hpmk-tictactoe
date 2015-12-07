package com.hipmunk.android.tictactoe.models.impl;

import com.hipmunk.android.tictactoe.Board;
import com.hipmunk.android.tictactoe.ComputerMoveEvaluation;
import com.hipmunk.android.tictactoe.models.IEvaluateTicTacToe;

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
    public int[] evaluateNextMove(Board board) {

        /*
        * 1. check if there is a spot that makes me win
        * 2. check if there is a spot that blocks other from winning
        */
        if (getMoveCount() >= 2) {
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

            if (myOptions.hasAvailableMoves()) {
                return myOptions.getRandomAvailableMove();
            }
        }

        int x = mRandom.nextInt(board.getRow());
        int y = mRandom.nextInt(board.getColumn());
        while (!board.isAvailable(x, y)) {
            x = mRandom.nextInt(board.getRow());
            y = mRandom.nextInt(board.getColumn());
        }
        return new int[]{x, y};
    }
}
