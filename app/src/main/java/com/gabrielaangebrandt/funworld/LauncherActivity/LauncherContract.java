package com.gabrielaangebrandt.funworld.LauncherActivity;

/**
 * Created by Gabriela on 22.7.2017..
 */

public interface LauncherContract {
    interface LauncherView {

    }
    interface LauncherPresenter{
        void onStart();

        void onStop();
    }
}