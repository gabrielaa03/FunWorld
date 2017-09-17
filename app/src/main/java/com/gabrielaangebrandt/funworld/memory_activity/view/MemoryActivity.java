package com.gabrielaangebrandt.funworld.memory_activity.view;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.base.Converter;
import com.gabrielaangebrandt.funworld.base.SharedPrefs;
import com.gabrielaangebrandt.funworld.memory_activity.MemoryContract;
import com.gabrielaangebrandt.funworld.memory_activity.adapter.MyRecyclerAdapter;
import com.gabrielaangebrandt.funworld.memory_activity.presenter.MemoryPresenterImpl;
import com.gabrielaangebrandt.funworld.models.data_model.MemoryObject;
import com.gabrielaangebrandt.funworld.models.database.DatabaseManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemoryActivity extends AppCompatActivity implements MemoryContract.MemoryView {

    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.recyclerViewMemory)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapter adapter;
    MemoryContract.MemoryPresenter presenter;
    private String format;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
    private Date timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_layout);
        ButterKnife.bind(this);
        setTitle("Memory");
        presenter = new MemoryPresenterImpl(this);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecyclerAdapter(this);
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

    //get all flags
    @Override
    public void getDefinedDrawables(List<String> definedDrawables) {
        List<MemoryObject> objects = new ArrayList<>();
        for (String s : definedDrawables) {
            objects.add(new MemoryObject(getResources().getIdentifier(s, "drawable", getPackageName()), false, false, s));
        }
        adapter.addDataToAdapter(objects);
    }

    //set time in textView
    @Override
    public void sendTimeData(String format) {
        this.format = format;
        time.setText(format);
    }

    //show results in alert dialog
    public void showScore() {
        presenter.onStop();
        try {
            timer = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String bestScore = DatabaseManager.setMemoryHighscore("username", SharedPrefs.getSharedPrefs("username", this), timer);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Your score is:  " + format + "\n" +
                "Your best score is : " + bestScore)
                .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("Replay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.onStart();
                        presenter.setTimer();
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
