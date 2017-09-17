package com.gabrielaangebrandt.funworld.tilt_activity;

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
