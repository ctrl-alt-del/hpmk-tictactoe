package com.hipmunk.android.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
    private Random mRandom;
    private int mMoveCount;

    public ComputerPlayer(char marker) {
        super(marker);
        mRandom = new Random();
    }

    /**
     * logic to get the next move
     */
    public int[] nextMove(Board board) {

        /*
        * 1. check if there is a spot that makes me win
        * 2. check if there is a spot that blocks other from winning
        */
        if (mMoveCount >= 2) {
            // move my winning move
            MoveEvaluation myOptions = new MoveEvaluation(board, this);
            if (myOptions.hasWinningMove()) {
                return myOptions.getWinningMove();
            }

            // block enemy's winning move
            final ComputerPlayer computer = board.getComputer();
            MoveEvaluation enemyOptions = new MoveEvaluation(board, computer);
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

    public int[] move(Board board) {

        int[] move = nextMove(board);
        int x = move[0];
        int y = move[1];
        board.set(x, y, getMarker());
        mMoveCount++;
        return move;
    }
}
