package com.gabrielaangebrandt.funworld.memory_activity.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.memory_activity.MemoryContract;
import com.gabrielaangebrandt.funworld.memory_activity.adapter.MyRecyclerAdapter;
import com.gabrielaangebrandt.funworld.memory_activity.presenter.MemoryPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriela on 23.7.2017..
 */

public class MemoryActivity extends AppCompatActivity implements MemoryContract.MemoryView {

    @BindView(R.id.player1)
    TextView player1;
    @BindView(R.id.recyclerViewMemory) RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapter adapter;
    MemoryContract.MemoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_layout);
        ButterKnife.bind(this);

        presenter = new MemoryPresenterImpl(this);

        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyRecyclerAdapter();
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

    @Override
    public void getDefinedDrawables(List<String> definedDrawables) {
        List<Integer> identifiers = new ArrayList<>();
        for(String name : definedDrawables){
            identifiers.add(getResources().getIdentifier(name, "drawable", getPackageName()));
        }
        adapter.addDataToAdapter(identifiers);
    }
}
