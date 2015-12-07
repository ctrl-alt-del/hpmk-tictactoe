package com.hipmunk.android.tictactoe;

public class Player {
    private char mMarker;
    private int mMoveCount;

    public Player(char marker) {
        mMarker = marker;
        mMoveCount = 0;
    }

    public char getMarker() {
        return mMarker;
    }

    public int[] move(Board board, int x, int y) {
        board.set(x, y, getMarker());
        mMoveCount++;
        return new int[]{x, y};
    }

    public int getMoveCount() {
        return mMoveCount;
    }
}
