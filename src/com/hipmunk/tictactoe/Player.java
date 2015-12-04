package com.hipmunk.tictactoe;

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

    /**
     * logic to get the next move
     */
    public int[] nextMove(Board board) {

        /*
        * 1. check if there is a spot that makes me win
        * 2. check if there is a spot that blocks other from winning
        */
        if (mMoveCount >= 2) {
            for (int i = 0; i < board.getRow(); i++) {
                for (int j = 0; j < board.getColumn(); j++) {
                    if (board.isAvailable(i, j) && BoardUtils.checkWinner(board, i, j, getMarker(), false)) {
                        return new int[]{i, j};
                    }
                }
            }

            final char otherMarker = getMarker() == board.getPlayer().getMarker() ? board.getComputer().getMarker() : board.getPlayer().getMarker();
            List<Integer[]> availablePoints = new ArrayList<>();
            for (int i = 0; i < board.getRow(); i++) {
                for (int j = 0; j < board.getColumn(); j++) {
                    if (board.isAvailable(i, j)) {
                        availablePoints.add(new Integer[]{i, j});
                        if (BoardUtils.checkWinner(board, i, j, otherMarker, false)) {
                            return new int[]{i, j};
                        }
                    }
                }
            }

            if (availablePoints.size() > 0) {
                Integer[] point = availablePoints.get(mRandom.nextInt(availablePoints.size()));
                return new int[]{point[0], point[1]};
            }
        }

        int x = mRandom.nextInt(board.getRow());
        int y = mRandom.nextInt(board.getColumn());
        // TODO: fix potential infinite loop in here
        while (!board.isAvailable(x, y)) {
            x = mRandom.nextInt(board.getRow());
            y = mRandom.nextInt(board.getColumn());
        }
        return new int[]{x, y};
    }

    public char getMarker() {
        return mMarker;
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
}
