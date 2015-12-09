package com.hpmk.android.tictactoe.presenters;

import com.hpmk.android.tictactoe.views.IBaseView;

import java.lang.ref.SoftReference;

public abstract class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {
    private SoftReference<T> mSoftReferenceView;

    public BasePresenter(T view) {
        mSoftReferenceView = new SoftReference<>(view);
    }

    @Override
    public T getView() {
        return mSoftReferenceView.get();
    }
}