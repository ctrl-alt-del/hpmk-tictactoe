package com.hipmunk.android.tictactoe.presenters;

import com.hipmunk.android.tictactoe.views.IBaseView;

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