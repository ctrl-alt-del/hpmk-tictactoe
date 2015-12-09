package com.hpmk.android.tictactoe.presenters;

import com.hpmk.android.tictactoe.views.IBaseView;

public interface IBasePresenter<T extends IBaseView> {
    T getView();
}
