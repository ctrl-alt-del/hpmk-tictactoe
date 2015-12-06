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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Board mBoard;
    private ArrayAdapter<String> mAdapter;
    private List<String> mBoardRecord;
    private GridView mGridview;

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

        mBoardRecord = new ArrayList<>();

        mBoard = getGame();
        mBoardRecord.addAll(mBoard.toList());

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mBoardRecord);
        mGridview = (GridView) findViewById(R.id.gridview);
        mGridview.setAdapter(mAdapter);

        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
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
                mBoard = getGame();
                mBoardRecord.clear();
                mBoardRecord.addAll(mBoard.toList());
                mAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Board getGame() {

        int row = 3;
        int col = 3;
        Player player = new Player('O');
        Player computer = new Player('X');
        Board board = new Board(row, col, player, computer);

        // turn on/off board history
        boolean showHistory = true;
        int trial = 0;
        Player mover;
        int[] move;
        boolean gameCompleted = false;
        int maxNumberOfMoves = row * col;
        while (trial < maxNumberOfMoves && !gameCompleted) {

            // simulated player move;
            mover = trial % 2 == 0 ? player : computer;
            move = mover.move(board);

            if (showHistory) {
                board.print();
            }

            // no need check winner for the first 4 moves
            if (trial >= 4) {
                gameCompleted = board.hasWinner(mover, move);
            }

            if (!gameCompleted) {
                trial++;
                if (trial == maxNumberOfMoves) {
                    System.out.println("No winner");
                }
            } else {
                if (!showHistory) {
                    // print the final result if board history is not enabled
                    board.print();
                }
            }
        }
        return board;
    }
}
