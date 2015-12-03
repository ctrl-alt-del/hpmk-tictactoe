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
        return new int[]{mRandom.nextInt(mBoardWidth), 0};
    }

    public char getMarker() {
        return mMarker;
    }

    public int[] move(Board board) {
        return nextMove();
    }

    public int getMoveCount() {
        return mMoveCount;
    }
}
