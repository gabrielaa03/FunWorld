package com.gabrielaangebrandt.funworld.tilt_activity.view;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.base.SharedPrefs;
import com.gabrielaangebrandt.funworld.models.data_model.Player;
import com.gabrielaangebrandt.funworld.tilt_activity.TiltContract;
import com.gabrielaangebrandt.funworld.tilt_activity.presenter.TiltPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Gabriela on 23.7.2017..
 */

public class TiltActivity extends AppCompatActivity implements SensorEventListener, TiltContract.TiltView {

    TiltContract.TiltPresenter presenter;
    @BindView(R.id.iv_leftFlag)
    ImageView leftFlag;
    @BindView(R.id.iv_rightFlag)
    ImageView rightFlag;
    @BindView(R.id.tv_counryName)
    TextView tv_name;
    @BindView(R.id.tv_counter_false)
    TextView tv_false;
    @BindView(R.id.tv_counter_true)
    TextView tv_true;
    private String leftF = "", rightF = "";
    String nameFlag = "";
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long lastUpdate = 0;
    boolean isAnswered = false;
    private static final int TIMEOUT = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tilt_layout);
        ButterKnife.bind(this);
        presenter = new TiltPresenterImpl(this);
        setTitle("Right Flag");
        tv_false.setText("0");
        tv_true.setText("0");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
        nameFlag = top;
        leftFlag.setImageResource(getResources().getIdentifier(leftF, "drawable", getPackageName()));
        rightFlag.setImageResource(getResources().getIdentifier(rightF, "drawable", getPackageName()));
        tv_name.setText(nameFlag);
    }

    @Override
    public void sendAnimation(String side, final int counterFalse, final int counterTrue) {
        switch (side) {
            case "left":
                leftFlag.bringToFront();
                presenter.playSound(1);
                tv_false.setText(String.valueOf(counterFalse));
                tv_true.setText(String.valueOf(counterTrue));

                break;
            case "right":
                rightFlag.bringToFront();
                presenter.playSound(0);
                tv_false.setText(String.valueOf(counterFalse));
                tv_true.setText(String.valueOf(counterTrue));

                break;

        }
        if (counterFalse + counterTrue == 20) {
            Realm realm = Realm.getDefaultInstance();
            String username = SharedPrefs.getSharedPrefs("username", this);
            String password = SharedPrefs.getSharedPrefs("password", this);
            realm.beginTransaction();
            Player user = realm.where(Player.class).equalTo("username", username).equalTo("password", password).findFirst();
            if (user != null) {
                if (user.getHsTilt() < counterTrue) {
                    user.setHsTilt(counterTrue);
                }
            }
            realm.copyToRealmOrUpdate(user);
            realm.commitTransaction();

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("Your score is:  " + counterTrue + "\n" +
                    "Your best score is : " + user.getHsTilt())
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
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float y = event.values[1];
        if (System.currentTimeMillis() - lastUpdate >= TIMEOUT) {
            lastUpdate = System.currentTimeMillis();
            if (!isAnswered && y < -3) {
                presenter.checkAnswer(this, "leftFlag", nameFlag);
                isAnswered = true;
                return;
            }
            if (!isAnswered && y > 3) {
                presenter.checkAnswer(this, "rightFlag", nameFlag);
                isAnswered = true;
                return;
            }
            if (isAnswered && (y > -3 && y < 3)) {
                isAnswered = false;
                presenter.onStart();
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
        sensorManager.unregisterListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }
}
