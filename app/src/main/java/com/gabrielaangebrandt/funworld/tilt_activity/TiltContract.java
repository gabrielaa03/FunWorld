package com.gabrielaangebrandt.funworld.tilt_activity;

import android.content.Context;
import android.media.SoundPool;
import android.view.animation.Animation;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public interface TiltContract {
    interface TiltView {

        void sendNumbers(String left, String right, String top);

        void sendAction(int counterFalse, int counterTrue);

        void playSound(int sound);
    }

    interface TiltPresenter {
        void onStart();

        void onStop();

        void checkAnswer(String side, String nameFlag);
    }
}
