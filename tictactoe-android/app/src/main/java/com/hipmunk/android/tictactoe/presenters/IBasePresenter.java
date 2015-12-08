package com.hipmunk.android.tictactoe.presenters;

import com.hipmunk.android.tictactoe.views.IBaseView;

public interface IBasePresenter<T extends IBaseView> {
    T getView();
}
