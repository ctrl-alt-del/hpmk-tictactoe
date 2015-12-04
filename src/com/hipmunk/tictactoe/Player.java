package com.hipmunk.tictactoe;

import java.util.Random;

public class Player {
    private Random mRandom;
    private char mMarker;
    private int mMoveCount;
    private int mBoardWidth;

    public Player(char marker) {
        mRandom = new Random();
        mMarker = marker;
        mMoveCount = 0;
        mBoardWidth = 3;
    }

    /**
     * logic to get the next move
     */
    public int[] nextMove() {
        return new int[]{mRandom.nextInt(mBoardWidth), mRandom.nextInt(mBoardWidth)};
    }

    public char getMarker() {
        return mMarker;
    }

    public int[] move(Board board) {

        boolean moveSucceed = false;
        int[] move = new int[2];
        while (!moveSucceed) {

            move = nextMove();
            int x = move[0];
            int y = move[1];

            // check if the place is within bounds and is taken already
            if (board.isAvailable(x, y) && !board.isTaken(x, y)) {
                board.set(x, y, getMarker());
                board.setTaken(x, y, true);
                moveSucceed = true;
            }
        }
        mMoveCount++;
        return move;
    }

    public int getMoveCount() {
        return mMoveCount;
    }
}
