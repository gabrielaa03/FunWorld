package com.gabrielaangebrandt.funworld.tilt_activity.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.tilt_activity.TiltContract;
import com.gabrielaangebrandt.funworld.tilt_activity.presenter.TiltPresenterImpl;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Gabriela on 23.7.2017..
 */

public class TiltActivity extends AppCompatActivity implements TiltContract.TiltView {
    TiltContract.TiltPresenter presenter;
    @BindView(R.id.leftFlag)
    ImageView leftFlag;
    @BindView(R.id.rightFlag) ImageView rightFlag;
    String leftF = "", rightF = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tilt_layout);
        presenter = new TiltPresenterImpl(this);
        leftFlag.setImageResource(getResources().getIdentifier(leftF, "drawables", getPackageName()));
        rightFlag.setImageResource(getResources().getIdentifier(rightF, "drawables", getPackageName()));
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
    public void sendNumbers(String left, String right) {
       leftF = left;
       rightF = right;
    }
}
