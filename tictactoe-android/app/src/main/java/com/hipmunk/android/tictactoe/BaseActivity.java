package com.hipmunk.android.tictactoe;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.hipmunk.android.tictactoe.views.IBaseView;

public class BaseActivity extends AppCompatActivity implements IBaseView {
    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
