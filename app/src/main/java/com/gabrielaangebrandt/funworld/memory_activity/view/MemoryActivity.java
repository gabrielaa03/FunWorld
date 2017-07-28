package com.gabrielaangebrandt.funworld.memory_activity.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.memory_activity.adapter.MyRecyclerAdapter;
import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.memory_activity.MemoryContract;
import com.gabrielaangebrandt.funworld.memory_activity.presenter.MemoryPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriela on 23.7.2017..
 */

public class MemoryActivity extends AppCompatActivity implements MemoryContract.MemoryView {

   /* @BindView(R.id.player1) TextView player1;
    @BindView(R.id.player2) TextView player2;*/

/*    @BindView(R.id.recyclerViewMemory) */RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    MyRecyclerAdapter adapter;
    MemoryContract.MemoryPresenter presenter;

    int[] drawables = new int[] {R.drawable.hr, R.drawable.fr, R.drawable.gb, R.drawable.lt, R.drawable.hr, R.drawable.fr, R.drawable.gb, R.drawable.lt};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //ButterKnife.bind(this);

        presenter = new MemoryPresenterImpl(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMemory);

        layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);


       adapter = new MyRecyclerAdapter(drawables);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
