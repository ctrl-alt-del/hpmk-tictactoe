package com.hpmk.android.tictactoe.presenters;

import com.hpmk.android.tictactoe.models.impl.TicTacToeBoard;
import com.hpmk.android.tictactoe.views.IMainView;

public interface IMainPresenter extends IBasePresenter<IMainView> {
    void performHumanMove(TicTacToeBoard board, int x, int y);
    void performComputerMove(TicTacToeBoard board, int x, int y);
    void performComputerMove(TicTacToeBoard board, int[] move);
}
