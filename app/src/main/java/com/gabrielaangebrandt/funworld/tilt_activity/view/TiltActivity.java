package com.gabrielaangebrandt.funworld.tilt_activity.view;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.tilt_activity.TiltContract;
import com.gabrielaangebrandt.funworld.tilt_activity.presenter.TiltPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gabriela on 23.7.2017..
 */

public class TiltActivity extends AppCompatActivity implements SensorEventListener, TiltContract.TiltView {

    TiltContract.TiltPresenter presenter;
    @BindView(R.id.iv_leftFlag) ImageView leftFlag;
    @BindView(R.id.iv_rightFlag) ImageView rightFlag;
    @BindView(R.id.tv_counryName) TextView tv_name;
    @BindView(R.id.tv_counter_false) TextView tv_false;
    @BindView(R.id.tv_counter_true) TextView tv_true;
    private String leftF = "", rightF = ""; String nameFlag = "";
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    OrientationEventListener orientationListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tilt_layout);
        ButterKnife.bind(this);
        presenter = new TiltPresenterImpl(this);

        tv_false.setText("0");
        tv_true.setText("0");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        orientationListener = createOrientationListener();
        orientationListener.enable();
    }

    private OrientationEventListener createOrientationListener() {
        return new OrientationEventListener(this) {
            public void onOrientationChanged(int orientation) {
                System.out.println("orientation................."+orientation);
            }
        };
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
        orientationListener.disable();
    }

    @Override
    public void sendNumbers(String left, String right, String top) {
       leftF = left;
       rightF = right;
       nameFlag= top;
       leftFlag.setImageResource(getResources().getIdentifier(leftF, "drawable", getPackageName()));
       rightFlag.setImageResource(getResources().getIdentifier(rightF, "drawable", getPackageName()));
       tv_name.setText(nameFlag);
    }

    @Override
    public void sendAnimation(String side, final int counterFalse, final int counterTrue) {
        switch (side) {
            case "left":
                leftFlag.bringToFront();
                presenter.playSound(1);/*
                leftFlag.startAnimation(animation);*/
                tv_false.setText(String.valueOf(counterFalse));
                tv_true.setText(String.valueOf(counterTrue));

                break;
            case "right":
                rightFlag.bringToFront();
                presenter.playSound(0);/*
                rightFlag.startAnimation(animation);*/
                tv_false.setText(String.valueOf(counterFalse));
                tv_true.setText(String.valueOf(counterTrue));

                break;

        }
            if (counterFalse + counterTrue == 20) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setMessage("Score: " + counterTrue)
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                presenter.onStart();

                            }

                        }).show();
            } else {
                final Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        presenter.onStart();

                    }
                }, 1000);

            }
    }
    @OnClick(R.id.iv_leftFlag)
    public void checkAnswer1(View view){
        presenter.checkAnswer(this,"leftFlag", nameFlag);
    }

    @OnClick(R.id.iv_rightFlag)
    public void checkAnswer2(View view){
        presenter.checkAnswer(this,"rightFlag", nameFlag);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        if (Math.abs(x) > Math.abs(y)) {
            if (x < 0) {
                presenter.checkAnswer(this,"leftFlag", nameFlag);
                Log.d("success", "You tilt the device left");
            }
            if (x > 0) {
                presenter.checkAnswer(this,"rightFlag", nameFlag);
                Log.d("success", "You tilt the device right");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }
}
