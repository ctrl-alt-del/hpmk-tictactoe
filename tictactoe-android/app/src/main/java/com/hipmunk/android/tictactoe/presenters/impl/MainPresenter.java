package com.hipmunk.android.tictactoe.presenters.impl;

import com.hipmunk.android.tictactoe.Board;
import com.hipmunk.android.tictactoe.presenters.BasePresenter;
import com.hipmunk.android.tictactoe.presenters.IMainPresenter;
import com.hipmunk.android.tictactoe.views.IMainView;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {

    public MainPresenter(IMainView view) {
        super(view);
    }

    @Override
    public void performHumanMove(Board board, int x, int y) {

    }

    @Override
    public void performComputerMove(Board board, int x, int y) {

    }
}
