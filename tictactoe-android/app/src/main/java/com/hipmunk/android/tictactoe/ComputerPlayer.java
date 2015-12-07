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

    public int getMoveCount() {
        return mMoveCount;
    }

    private class MoveEvaluation {
        private int[] mWinningMove;
        private boolean mHasWinningMove;
        private List<Integer[]> mAvailableMoves;

        public MoveEvaluation(Board board, ComputerPlayer mover) {
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
