package com.hipmunk.android.tictactoe.views;

import com.hipmunk.android.tictactoe.models.impl.Player;

public interface IMainView extends IBaseView {
    void onPlayerMoveSucceed(int[] move);
    void onPlayerMoveFailed(String errorMessage);
    void onComputerMoveSucceed(int[] move);
    void onComputerMoveFailed(String errorMessage);
    void onGameOver(boolean hasWinner, Player player);
}
