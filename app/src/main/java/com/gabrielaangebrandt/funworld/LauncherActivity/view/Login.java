package com.gabrielaangebrandt.funworld.LauncherActivity.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.gabrielaangebrandt.funworld.ForgottenPassword.view.ForgottenPassword;
import com.gabrielaangebrandt.funworld.LauncherActivity.LauncherContract;
import com.gabrielaangebrandt.funworld.LauncherActivity.presenter.LauncherPresenterImpl;
import com.gabrielaangebrandt.funworld.MainActivity.view.MainActivity;
import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.models.database.DatabaseConfig;
import com.gabrielaangebrandt.funworld.models.data_model.Player;
import com.gabrielaangebrandt.funworld.register_activity.view.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class Login extends AppCompatActivity implements LauncherContract.LauncherView {
    @BindView(R.id.et_password)
    EditText password;
    @BindView(R.id.et_username)
    EditText username;
    LauncherContract.LauncherPresenter presenter;
    Realm realm;
    TextInputLayout text_input_layout1;
    TextInputLayout text_input_layout2;
    public boolean isLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        ButterKnife.bind(this);
        presenter = new LauncherPresenterImpl(this);
         /*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        isLogin = prefs.getBoolean("Islogin", false);
        if(isLogin)
        {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else {

        }*/

        /*text_input_layout1= (TextInputLayout) findViewById(R.id.text_input_layout);
        text_input_layout2= (TextInputLayout) findViewById(R.id.text_input_layout1);
        text_input_layout1.setHint("Username");
        text_input_layout2.setHint("Password");*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @OnClick(R.id.btn_login1)
    public void openMainActivity() {
        realm = DatabaseConfig.getRealmInstance();
        Player user = realm.where(Player.class).equalTo("username", username.getText().toString()).equalTo("password", password.getText().toString()).findFirst();

        if (password.getText().toString().equals("") || username.getText().toString().equals("")) {
            Toast.makeText(this, R.string.userDoesNotExists, Toast.LENGTH_LONG).show();
        } else {
            if ( user != null) {
                setDefaults("username", username.getText().toString(), this);
                setDefaults("password", password.getText().toString(), this);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.userDoesNotExists, Toast.LENGTH_LONG).show();
            }
        }
    }

    @OnClick(R.id.btn_register)
    public void openRegisterActivity() {
        Intent intent1 = new Intent(this, RegisterActivity.class);
        startActivity(intent1);
    }

    @OnClick(R.id.tv_forgotten_pass)
    public void openForgottenPassActivity() {
        Intent intent2 = new Intent(this, ForgottenPassword.class);
        startActivity(intent2);
    }

    @Override
    protected void onDestroy() {
        if(realm != null) {
            realm.close();
        }
        super.onDestroy();
    }

    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}
