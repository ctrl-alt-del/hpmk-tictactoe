package com.hipmunk.android.tictactoe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.hipmunk.android.tictactoe.models.impl.ComputerPlayer;
import com.hipmunk.android.tictactoe.models.impl.HumanPlayer;
import com.hipmunk.android.tictactoe.models.impl.Player;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Board mBoard;
    private ArrayAdapter<String> mAdapter;
    private List<String> mBoardRecord;
    private GridView mGridview;
    private HumanPlayer mHumanPlayer;
    private ComputerPlayer mComputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                int y = position % 3;
                int x = (position - y) / 3;
                // TODO: move to presenter, then attach the computer move
                int[] move = mHumanPlayer.move(mBoard, x, y);
                onPlayerMoveSucceed(move);
                // TODO: evaluate winner after move

                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
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

    // TODO: put this method to interface
    public void onPlayerMoveSucceed(int[] move) {
        updateGridView();
        if (BoardUtils.checkWinner(mBoard, move)) {
            Snackbar.make(findViewById(android.R.id.content), getString(R.string.winning_message), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            mGridview.setEnabled(false);
        } else {
            performComputerMove();
        }
    }

    // TODO: move this method to presenter
    public void performComputerMove() {
        int[] nextComputerMove = mComputer.evaluateNextMove(mBoard);
        mComputer.move(mBoard, nextComputerMove);
        onComputerMoveSucceed(nextComputerMove);
    }

    // TODO: put this method to interface
    public void onComputerMoveSucceed(int[] move) {
        updateGridView();
        if (BoardUtils.checkWinner(mBoard, move)) {
            Snackbar.make(findViewById(android.R.id.content), getString(R.string.defeated_message), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            mGridview.setEnabled(false);
        }
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
        mGridview = (GridView) findViewById(R.id.gridview);
        mGridview.setAdapter(mAdapter);
        mGridview.setEnabled(true);
    }
}
