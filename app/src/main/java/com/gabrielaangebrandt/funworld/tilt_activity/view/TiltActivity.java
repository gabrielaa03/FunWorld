package com.gabrielaangebrandt.funworld.tilt_activity.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.tilt_activity.TiltContract;
import com.gabrielaangebrandt.funworld.tilt_activity.presenter.TiltPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriela on 23.7.2017..
 */

public class TiltActivity extends AppCompatActivity implements SensorEventListener, TiltContract.TiltView {

    TiltContract.TiltPresenter presenter;
    @BindView(R.id.iv_leftFlag) ImageView leftFlag;
    @BindView(R.id.iv_rightFlag) ImageView rightFlag;
    @BindView(R.id.tv_counryName) TextView tv_name;
    private String leftF = "", rightF = ""; String nameFlag = "";
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tilt_layout);
        ButterKnife.bind(this);
        presenter = new TiltPresenterImpl(this);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
    public void sendNumbers(String left, String right, String top) {
       leftF = left;
       rightF = right;
       nameFlag= top;
       leftFlag.setImageResource(getResources().getIdentifier(leftF, "drawable", getPackageName()));
       rightFlag.setImageResource(getResources().getIdentifier(rightF, "drawable", getPackageName()));
       tv_name.setText(nameFlag);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        if (Math.abs(x) > Math.abs(y)) {
            if (y < 0) {
                String leftSide = "left";
                presenter.checkAnswer(leftSide, nameFlag);
                Log.d("success", "You tilt the device left");
            }
            if (y > 0) {
                String rightSide = "right";
                presenter.checkAnswer(rightSide, nameFlag);
                Log.d("success", "You tilt the device right");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
