package com.hipmunk.tictactoe;

public class Main {

    public static void main(String[] args) {

        int row = 3;
        int col = 3;
        Player player = new Player('O');
        Player computer = new Player('X');
        Board board = new Board(row, col, player, computer);

        // turn on/off board history
        boolean showHistory = true;
        int trial = 0;
        Player mover;
        int[] move;
        boolean gameCompleted = false;
        int maxNumberOfMoves = row * col;
        while (trial < maxNumberOfMoves && !gameCompleted) {

            // simulated player move;
            mover = trial % 2 == 0 ? player : computer;
            move = mover.move(board);

            if (showHistory) {
                board.print();
            }

            // no need check winner for the first 4 moves
            if (trial >= 4) {
                gameCompleted = board.hasWinner(mover, move);
            }

            if (!gameCompleted) {
                trial++;
                if (trial == maxNumberOfMoves) {
                    System.out.println("No winner");
                }
            } else {
                if (!showHistory) {
                    // print the final result if board history is not enabled
                    board.print();
                }
            }
        }
    }
}
