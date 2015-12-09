package com.hpmk.android.tictactoe.models.impl;

public abstract class Player {
    private char mMarker;
    private int mMoveCount;

    public Player(char marker) {
        mMarker = marker;
        mMoveCount = 0;
    }

    public char getMarker() {
        return mMarker;
    }

    public int getMoveCount() {
        return mMoveCount;
    }

    public void incrementMoveCount() {
        mMoveCount++;
    }

    public void decrementMoveCount() {
        if (mMoveCount > 0) {
            mMoveCount--;
        }
    }
}
