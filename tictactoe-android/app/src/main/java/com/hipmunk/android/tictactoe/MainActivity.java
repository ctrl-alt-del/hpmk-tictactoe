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
import android.widget.Toast;

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

    private Board mBoard;
    private ArrayAdapter<String> mAdapter;
    private List<String> mBoardRecord;
    private GridView mGridView;
    private HumanPlayer mHumanPlayer;
    private ComputerPlayer mComputer;
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
                initializeGame();
                updateGridView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPlayerMoveSucceed(int[] move) {
        updateGridView();
        int[] nextComputerMove = mComputer.evaluateNextMove(mBoard);
        mPresenter.performComputerMove(mBoard, nextComputerMove[0], nextComputerMove[1]);
    }

    @Override
    public void onPlayerMoveFailed(String errorMessage) {

    }

    @Override
    public void onComputerMoveSucceed(int[] move) {
        updateGridView();
    }

    @Override
    public void onComputerMoveFailed(String errorMessage) {

    }

    @Override
    public void onGameOver(boolean hasWinner, Player player) {
        String message;
        if (hasWinner) {
            message = (player instanceof HumanPlayer) ? getString(R.string.winning_message) : getString(R.string.defeated_message);
        } else {
            message = getString(R.string.tie_message);
        }
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        mGridView.setEnabled(false);
    }

    public void updateGridView() {
        mBoardRecord.clear();
        mBoardRecord.addAll(mBoard.toList());
        mAdapter.notifyDataSetChanged();
    }

    public void initializeGame() {
        int row = 3;
        int col = 3;
        mHumanPlayer = new HumanPlayer('O');
        mComputer = new ComputerPlayer('X');
        mBoard = new Board(row, col, mHumanPlayer, mComputer);
        mBoardRecord = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, R.layout.board_item, mBoardRecord);
        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setAdapter(mAdapter);
        mGridView.setEnabled(true);
    }
}
