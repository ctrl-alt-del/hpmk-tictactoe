package com.hpmk.android.tictactoe.views;

import com.hpmk.android.tictactoe.models.impl.Player;

public interface IMainView extends IBaseView {
    void onPlayerMoveSucceed(int[] move);
    void onPlayerMoveFailed(String errorMessage);
    void onComputerMoveSucceed(int[] move);
    void onComputerMoveFailed(String errorMessage);
    void onGameOverWithWinner(Player player);
    void onGameOverWithoutWinner();
}
