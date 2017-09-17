package com.gabrielaangebrandt.funworld.tilt_activity.view;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.base.SharedPrefs;
import com.gabrielaangebrandt.funworld.models.data_model.Player;
import com.gabrielaangebrandt.funworld.models.database.DatabaseManager;
import com.gabrielaangebrandt.funworld.tilt_activity.TiltContract;
import com.gabrielaangebrandt.funworld.tilt_activity.presenter.TiltPresenterImpl;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

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
    private String nameFlag = "";
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long lastUpdate = 0;
    boolean isAnswered = false;
    private static final int TIMEOUT = 200;
    private SoundPool soundPool;
    boolean soundLoaded = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tilt_layout);
        ButterKnife.bind(this);
        presenter = new TiltPresenterImpl(this);
        this.loadSounds();
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
    public void sendAction(final int counterFalse, final int counterTrue) {
        tv_false.setText(String.valueOf(counterFalse));
        tv_true.setText(String.valueOf(counterTrue));
        if (counterFalse + counterTrue == 20) {

            int bestScore = DatabaseManager.setTiltHighscore("username", SharedPrefs.getSharedPrefs("username", this), counterTrue);

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("Your score is:  " + counterTrue + "\n" +
                    "Your best score is : " + bestScore)
                    .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("Replay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            tv_false.setText("0");
                            tv_true.setText("0");
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
                presenter.checkAnswer("leftFlag", nameFlag);
                isAnswered = true;
                return;
            }
            if (!isAnswered && y > 3) {
                presenter.checkAnswer("rightFlag", nameFlag);
                isAnswered = true;
                return;
            }
            if (isAnswered && (y > -3 && y < 3)) {
                isAnswered = false;
                presenter.onStart();
            }
        }
    }

    private void loadSounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.soundPool = new SoundPool.Builder().setMaxStreams(1000).build();
        } else {
            this.soundPool = new SoundPool(1000, AudioManager.STREAM_MUSIC, 0);
        }
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundLoaded = true;
            }
        });
      this.soundPool.load(this, R.raw.dustyroom_multimedia_incorrect_negative_tone, 1);
      this.soundPool.load(this, R.raw.dustyroom_multimedia_correct_complete_bonus, 2);
    }

    public void playSound(int soundID) {
        soundPool.play(soundID, 1, 1, 1, 0, 1f);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
