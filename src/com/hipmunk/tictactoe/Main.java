package com.hipmunk.tictactoe;

public class Main {

    public static void main(String[] args) {

        int height = 3;
        int width = 3;
        Board board = new Board(width, height);
        Player player = new Player('O');
        Player computer = new Player('X');
        player.move(board);

        // turn on/off board history
        int trial = 0;
        Player mover;
        int[] move;
        boolean gameCompleted = false;
        int maxNumberOfMoves = height * width;
        while (trial < maxNumberOfMoves && !gameCompleted) {

            // simulated player move;
            mover = trial % 2 == 0 ? player : computer;
            move = mover.move(board);

            board.print();
            trial++;
            if (trial == maxNumberOfMoves) {
                System.out.println("No winner");
            }
        }
    }
}
