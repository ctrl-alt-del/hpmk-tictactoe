package com.hipmunk.android.tictactoe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.hipmunk.android.tictactoe.models.impl.ComputerPlayer;
import com.hipmunk.android.tictactoe.models.impl.HumanPlayer;
import com.hipmunk.android.tictactoe.models.impl.Player;
import com.hipmunk.android.tictactoe.presenters.IMainPresenter;
import com.hipmunk.android.tictactoe.presenters.impl.MainPresenter;
import com.hipmunk.android.tictactoe.views.IMainView;
import com.hipmunk.android.tictactoe.views.impl.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IMainView {

    private TicTacToeBoard mBoard;
    private ArrayAdapter<String> mAdapter;
    private List<String> mBoardRecord;
    private GridView mGridView;
    private HumanPlayer mHumanPlayer;
    private ComputerPlayer mComputerPlayer;
    private IMainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPresenter = new MainPresenter(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mBoardRecord = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, R.layout.board_item, mBoardRecord);
        mGridView = (GridView) findViewById(R.id.gridview);

        initializeGame();
        mBoardRecord.addAll(mBoard.toList());

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                int y = position % 3;
                int x = (position - y) / 3;
                mPresenter.performHumanMove(mBoard, x, y);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_restart:
                resetGame();
                updateGridView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPlayerMoveSucceed(int[] move) {
        updateGridView();
        int[] nextComputerMove = mComputerPlayer.evaluateNextMove(mBoard);
        mPresenter.performComputerMove(mBoard, nextComputerMove);
    }

    @Override
    public void onPlayerMoveFailed(String errorMessage) {
        // TODO: add dialog to show error message
    }

    @Override
    public void onComputerMoveSucceed(int[] move) {
        updateGridView();
    }

    @Override
    public void onComputerMoveFailed(String errorMessage) {
        // TODO: add dialog to show error message
    }

    @Override
    public void onGameOverWithWinner(Player player) {
        updateGridView();
        String message = (player instanceof HumanPlayer) ? getString(R.string.winning_message) : getString(R.string.defeated_message);
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        mGridView.setEnabled(false);
    }

    @Override
    public void onGameOverWithoutWinner() {
        updateGridView();
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.tie_message), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        mGridView.setEnabled(false);
    }

    public void updateGridView() {
        mBoardRecord.clear();
        mBoardRecord.addAll(mBoard.toList());
        mAdapter.notifyDataSetChanged();
    }

    public void initializeGame() {
        mHumanPlayer = new HumanPlayer('O');
        mComputerPlayer = new ComputerPlayer('X');

        int row = 3;
        int col = 3;
        mBoard = new TicTacToeBoard(row, col, mHumanPlayer, mComputerPlayer);

        mGridView.setAdapter(mAdapter);
        mGridView.setEnabled(true);
    }

    public void resetGame() {
        mBoard.reset();
        mGridView.setEnabled(true);
    }
}
