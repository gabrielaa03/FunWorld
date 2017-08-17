package com.gabrielaangebrandt.funworld.forgotten_password_activity;

/**
 * Created by Plava tvornica on 17.8.2017..
 */

public interface ForgottenPasswordContract {
    interface ForgottenPasswordView{

    }
    interface ForgottenPasswordPresenter{
        String checkValues(String databaseColumn, String value, String selectedItem, String answer);

        void onStart();

        void onStop();
    }
}
