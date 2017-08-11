package com.gabrielaangebrandt.funworld.memory_activity.view;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.LauncherActivity.view.Login;
import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.memory_activity.MemoryContract;
import com.gabrielaangebrandt.funworld.memory_activity.adapter.MyRecyclerAdapter;
import com.gabrielaangebrandt.funworld.memory_activity.presenter.MemoryPresenterImpl;
import com.gabrielaangebrandt.funworld.models.data_model.MemoryObject;
import com.gabrielaangebrandt.funworld.models.data_model.Player;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Gabriela on 23.7.2017..
 */

public class MemoryActivity extends AppCompatActivity implements MemoryContract.MemoryView {

    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.recyclerViewMemory) RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapter adapter;
    MemoryContract.MemoryPresenter presenter;
    long score; String format, timeFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_layout);
        ButterKnife.bind(this);

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

    @Override
    public void getDefinedDrawables(List<String> definedDrawables) {
        List<MemoryObject> objects = new ArrayList<>();
        for(String s : definedDrawables){
            objects.add(new MemoryObject(getResources().getIdentifier(s, "drawable", getPackageName()), false, false, s));
        }
        adapter.addDataToAdapter(objects);
    }

    @Override
    public void sendTimeData(String format){
        this.format = format;
        time.setText(format);
    }

    public long getStartTime(){return System.currentTimeMillis();}

    public void showScore(){
        presenter.onStop();
        getTimeInLong();
        Realm realm = Realm.getDefaultInstance();
        String username = Login.getDefaults("username", this);
        String password = Login.getDefaults("password", this);
        realm.beginTransaction();
        Player user = realm.where(Player.class).equalTo("username", username).equalTo("password", password).findFirst();
            if(user != null){
                if(user.getHsMemory()< score){
                    user.setHsMemory(score);
                }
                getLongtoTime(user.getHsMemory());
            }

        Player realmUser = realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Your score is:  " + format + "\n" +
                            "Your best score is : " + timeFormat)
                .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("Replay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.onStart();
                    }
                }).show();
    }

    public long getTimeInLong(){
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        Date dateObj= null;
        try {
            dateObj = sdf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long newScore = dateObj.getTime();
        return score = Math.abs(newScore);

    }

    public String getLongtoTime(long l){
        long ll = l * (-1);
        return timeFormat = DateFormat.format("mm:ss", new Date(ll)).toString();
    }
}
