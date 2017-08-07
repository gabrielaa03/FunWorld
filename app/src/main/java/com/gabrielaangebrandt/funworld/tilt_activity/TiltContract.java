package com.gabrielaangebrandt.funworld.tilt_activity;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public interface TiltContract {
    interface TiltView{

        void sendNumbers(String left, String right, String top);
    }
    interface  TiltPresenter{
        void onStart();
        void onStop();

        void checkAnswer(String side, String nameFlag);
    }
}
