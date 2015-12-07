package com.hipmunk.android.tictactoe;

import com.hipmunk.android.tictactoe.models.impl.Player;

/**
 * Method to check the winner
 */
public class BoardUtils {

    public static boolean checkWinner(Board board, int[] move) {
        int x = move[0];
        int y = move[1];
        return checkWinner(board, x, y, board.get(x, y), true);
    }

    public static boolean checkWinner(Board board, int x, int y) {
        return checkWinner(board, x, y, board.get(x, y), true);
    }

    public static boolean checkWinner(Board board, int x, int y, int marker, boolean showLog) {
        int flag = marker;
        int horizontalCount = 1;
        int verticalCount = 1;
        int diagonalCount = 1;
        int antiDiagonalCount = 1;
        int winningSum = 3;

        // booleans used to stop checks on a direction as early as possible
        boolean checkEast = true;
        boolean checkWest = true;
        boolean checkNorth = true;
        boolean checkSouth = true;
        boolean checkSouthWest = true;
        boolean checkNorthEast = true;
        boolean checkNorthWest = true;
        boolean checkSouthEast = true;

        // the current (x, y) is always valid, so we just need to check the other 2 in all direction
        for (int i = 1; i < 3; i++) {
            /*
             * stop in a direction if
             * (1) we know it didn't work,
             * (2) out of bound, or
             * (3) it is occupied by other player
             */
            if (!checkEast || !board.isWithinBound(x, y + i) || board.get(x, y + i) != flag) {
                checkEast = false;
            } else {
                horizontalCount++;
            }

            if (!checkWest || !board.isWithinBound(x, y - i) || board.get(x, y - i) != flag) {
                checkWest = false;
            } else {
                horizontalCount++;
            }

            if (horizontalCount == winningSum) {
                showLog(showLog, "won the game because of horizontal connected");
                return true;
            }

            if (!checkNorth || !board.isWithinBound(x - i, y) || board.get(x - i, y) != flag) {
                checkNorth = false;
            } else {
                verticalCount++;
            }

            if (!checkSouth || !board.isWithinBound(x + i, y) || board.get(x + i, y) != flag) {
                checkSouth = false;
            } else {
                verticalCount++;
            }

            if (verticalCount == winningSum) {
                showLog(showLog, "won the game because of vertical connected");
                return true;
            }

            if (!checkNorthEast || !board.isWithinBound(x - i, y + i) || board.get(x - i, y + i) != flag) {
                checkNorthEast = false;
            } else {
                antiDiagonalCount++;
            }

            if (!checkSouthWest || !board.isWithinBound(x + i, y - i) || board.get(x + i, y - i) != flag) {
                checkSouthWest = false;
            } else {
                antiDiagonalCount++;
            }

            if (antiDiagonalCount == winningSum) {
                showLog(showLog, "won the game because of / diagonal connected");
                return true;
            }

            if (!checkNorthWest || !board.isWithinBound(x - i, y - i) || board.get(x - i, y - i) != flag) {
                checkNorthWest = false;
            } else {
                diagonalCount++;
            }

            if (!checkSouthEast || !board.isWithinBound(x + i, y + i) || board.get(x + i, y + i) != flag) {
                checkSouthEast = false;
            } else {
                diagonalCount++;
            }

            if (diagonalCount == winningSum) {
                showLog(showLog, "won the game because of \\ diagonal connected");
                return true;
            }
        }
        return false;
    }

    public static void announceWinner(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getMarker());
        sb.append(" won the game on its move #");
        sb.append(player.getMoveCount());
        System.out.println(sb.toString());
    }

    public static void showLog(boolean showLog, String message) {
        if (showLog) {
            System.out.println(message);
        }
    }
}
