package com.hipmunk.android.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private Random mRandom;
    private char mMarker;
    private int mMoveCount;

    public Player(char marker) {
        mRandom = new Random();
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

    private class MoveEvaluation {
        private int[] mWinningMove;
        private boolean mHasWinningMove;
        private List<Integer[]> mAvailableMoves;

        public MoveEvaluation(Board board, Player mover) {
            mAvailableMoves = new ArrayList<>();
            for (int i = 0; i < board.getRow(); i++) {
                for (int j = 0; j < board.getColumn(); j++) {
                    if (board.isAvailable(i, j)) {
                        mAvailableMoves.add(new Integer[]{i, j});
                        if (BoardUtils.checkWinner(board, i, j, mover.getMarker(), false)) {
                            mWinningMove = new int[]{i, j};
                            mHasWinningMove = true;
                        }
                    }
                }
            }
        }

        public boolean hasWinningMove() {
            return mHasWinningMove;
        }

        public int[] getWinningMove() {
            return mWinningMove;
        }

        public boolean hasAvailableMoves() {
            return mAvailableMoves.size() > 0;
        }

        public int[] getRandomAvailableMove() {
            Integer[] point = mAvailableMoves.get(mRandom.nextInt(mAvailableMoves.size()));
            return new int[]{point[0], point[1]};
        }
    }
}
