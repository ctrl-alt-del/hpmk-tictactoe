package com.hipmunk.android.tictactoe.presenters;

import com.hipmunk.android.tictactoe.TicTacToeBoard;
import com.hipmunk.android.tictactoe.views.IMainView;

public interface IMainPresenter extends IBasePresenter<IMainView> {
    void performHumanMove(TicTacToeBoard board, int x, int y);
    void performComputerMove(TicTacToeBoard board, int x, int y);
    void performComputerMove(TicTacToeBoard board, int[] move);
}
