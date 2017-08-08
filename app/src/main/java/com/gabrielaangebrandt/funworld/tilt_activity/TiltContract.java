package com.gabrielaangebrandt.funworld.tilt_activity;

import android.content.Context;
import android.view.animation.Animation;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public interface TiltContract {
    interface TiltView{

        void sendNumbers(String left, String right, String top);

        void sendAnimation(String side, Animation animation, int counterFalse, int counterTrue);
    }
    interface  TiltPresenter{
        void onStart();
        void onStop();

        void checkAnswer(Context context,String side, String nameFlag);
    }
}
