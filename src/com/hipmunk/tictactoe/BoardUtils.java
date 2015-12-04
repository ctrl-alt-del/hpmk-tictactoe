package com.hipmunk.tictactoe;

/**
 * Method to check the winner
 */
public class BoardUtils {

    public static boolean checkWinner(Board board, int x, int y) {

        int flag = board.get(x, y);
        int horizontalCount = 1;
        int verticalCount = 1;
        int diagonalCount = 1;
        int antiDiagonalCount = 1;
        int winningSum = 3;

        // booleans used to stop checks on a direction as early as possible
        boolean checkEast = true;
        boolean checkWest = true;
        // there is no need to check the north direction since there won't be any pre-existing item on the north direction
        boolean checkSouth = true;
        boolean checkSouthWest = true;
        boolean checkNorthEast = true;
        boolean checkNorthWest = true;
        boolean checkSouthEast = true;

        // the current (x, y) is always valid, so we just need to check the other 2 in all direction except the north
        for (int i = 1; i < 3; i++) {
            /*
             * stop in a direction if
             * (1) we know it didn't work,
             * (2) out of bound, or
             * (3) it is occupied by other player
             */
            if (!checkEast || !board.isWithinBound(x + i, y) || board.get(x + i, y) != flag) {
                checkEast = false;
            } else {
                horizontalCount++;
            }

            if (!checkWest || !board.isWithinBound(x - i, y) || board.get(x - i, y) != flag) {
                checkWest = false;
            } else {
                horizontalCount++;
            }

            if (horizontalCount == winningSum) {
                System.out.println("won the game because of horizontal connected");
                return true;
            }

            if (!checkSouth || !board.isWithinBound(x, y + i) || board.get(x, y + i) != flag) {
                checkSouth = false;
            } else {
                verticalCount++;
            }

            if (verticalCount == winningSum) {
                System.out.println("won the game because of vertical connected");
                return true;
            }

            if (!checkNorthEast || !board.isWithinBound(x + i, y - i) || board.get(x + i, y - i) != flag) {
                checkNorthEast = false;
            } else {
                antiDiagonalCount++;
            }

            if (!checkSouthWest || !board.isWithinBound(x - i, y + i) || board.get(x - i, y + i) != flag) {
                checkSouthWest = false;
            } else {
                antiDiagonalCount++;
            }

            if (antiDiagonalCount == winningSum) {
                System.out.println("won the game because of / diagonal connected");
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
                System.out.println("won the game because of \\ diagonal connected");
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
}
