package com.hipmunk.android.tictactoe.presenters.impl;

import android.support.annotation.NonNull;

import com.hipmunk.android.tictactoe.Board;
import com.hipmunk.android.tictactoe.BoardUtils;
import com.hipmunk.android.tictactoe.models.impl.ComputerPlayer;
import com.hipmunk.android.tictactoe.models.impl.HumanPlayer;
import com.hipmunk.android.tictactoe.presenters.BasePresenter;
import com.hipmunk.android.tictactoe.presenters.IMainPresenter;
import com.hipmunk.android.tictactoe.views.IMainView;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {

    public MainPresenter(IMainView view) {
        super(view);
    }

    @Override
    public void performHumanMove(@NonNull Board board, int x, int y) {
        HumanPlayer humanPlayer = board.getHumanPlayer();
        int[] move = humanPlayer.move(board, x, y);
        boolean hasWinner = BoardUtils.checkWinner(board, move);

        if (hasWinner) {
            getView().onGameOverWithWinner(humanPlayer);
        } else if (board.isGameEnd()) {
            getView().onGameOverWithoutWinner();
        } else {
            getView().onPlayerMoveSucceed(move);
        }
    }

    @Override
    public void performComputerMove(@NonNull Board board, int x, int y) {
        ComputerPlayer computerPlayer = board.getComputerPlayer();
        int[] move = computerPlayer.evaluateNextMove(board);
        computerPlayer.move(board, move);

        boolean hasWinner = BoardUtils.checkWinner(board, move);
        if (hasWinner) {
            getView().onGameOverWithWinner(computerPlayer);
        } else if (board.isGameEnd()) {
            getView().onGameOverWithoutWinner();
        } else {
            getView().onComputerMoveSucceed(move);
        }
    }

    @Override
    public void performComputerMove(@NonNull Board board, int[] move) {
        if (move.length != 2) {
            //TODO: add error message
            getView().onComputerMoveFailed("");
        }

        performHumanMove(board, move[0], move[1]);
    }
}
