package com.hpmk.android.tictactoe.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

public class MessageUtils {
    public static Snackbar createSnackbar(Activity activity, int messageResId, int actionLabelResId, View.OnClickListener actionCallback) {
        return Snackbar.make(activity.findViewById(android.R.id.content),
                activity.getString(messageResId), Snackbar.LENGTH_INDEFINITE)
                .setAction(actionLabelResId, actionCallback);
    }
}
