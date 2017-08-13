package com.gabrielaangebrandt.funworld.launcher_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.base.SharedPrefs;
import com.gabrielaangebrandt.funworld.login_activity.view.Login;
import com.gabrielaangebrandt.funworld.main_activity.view.MainActivity;

import butterknife.BindView;

public class LauncherActivity extends AppCompatActivity {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMax(50);
        if(SharedPrefs.getDefaults("isLoggedIn", this).equals(true)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else if(SharedPrefs.getDefaults("isLoggedIn", this).equals(false)){
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }
}
