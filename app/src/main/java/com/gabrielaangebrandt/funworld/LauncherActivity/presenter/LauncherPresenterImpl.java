package com.gabrielaangebrandt.funworld.LauncherActivity.presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.gabrielaangebrandt.funworld.LauncherActivity.LauncherContract;
import com.gabrielaangebrandt.funworld.LauncherActivity.view.Login;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public class LauncherPresenterImpl implements LauncherContract.LauncherPresenter{
    LauncherContract.LauncherView view;

    public LauncherPresenterImpl(LauncherContract.LauncherView view) {
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
