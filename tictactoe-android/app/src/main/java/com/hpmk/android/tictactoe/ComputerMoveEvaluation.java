package com.hpmk.android.tictactoe;

import com.hpmk.android.tictactoe.models.impl.Player;
import com.hpmk.android.tictactoe.models.impl.TicTacToeBoard;
import com.hpmk.android.tictactoe.utils.BoardUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerMoveEvaluation {
    private int[] mWinningMove;
    private boolean mHasWinningMove;
    private List<Integer[]> mAvailableMoves;
    private Random mRandom;

    public ComputerMoveEvaluation(TicTacToeBoard board, Player mover, Random random) {
        mRandom = random;
        mAvailableMoves = new ArrayList<>();
        List<Integer[]> highValueMoves = new ArrayList<>();
        List<Integer[]> mediumValueMoves = new ArrayList<>();
        List<Integer[]> lowValueMoves = new ArrayList<>();

        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getColumn(); j++) {
                if (board.isAvailable(i, j)) {

                    if (board.isCenter(i, j)) {
                        // highest value in all pending move except winning move
                        mAvailableMoves.add(new Integer[]{i, j});
                    } else if (board.isCorner(i, j)) {
                        highValueMoves.add(new Integer[]{i, j});
                    } else if (board.isEdge(i, j)) {
                        mediumValueMoves.add(new Integer[]{i, j});
                    } else {
                        lowValueMoves.add(new Integer[]{i, j});
                    }

                    if (BoardUtils.checkWinner(board, i, j, mover.getMarker(), false)) {
                        mWinningMove = new int[]{i, j};
                        mHasWinningMove = true;
                    }
                }
            }
        }
        mAvailableMoves.addAll(highValueMoves);
        mAvailableMoves.addAll(mediumValueMoves);
        mAvailableMoves.addAll(lowValueMoves);
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

    public int[] getMostValuedAvailableMove() {
        Integer[] point = mAvailableMoves.get(0);
        return new int[]{point[0], point[1]};
    }
}
