package com.hipmunk.android.tictactoe.presenters;

import com.hipmunk.android.tictactoe.Board;
import com.hipmunk.android.tictactoe.views.IMainView;

public interface IMainPresenter extends IBasePresenter<IMainView> {
    void performHumanMove(Board board, int x, int y);
    void performComputerMove(Board board, int x, int y);
    void performComputerMove(Board board, int[] move);
}
