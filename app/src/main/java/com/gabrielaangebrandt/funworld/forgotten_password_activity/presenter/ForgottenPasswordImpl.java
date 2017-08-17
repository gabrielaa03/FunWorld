package com.gabrielaangebrandt.funworld.forgotten_password_activity.presenter;

import com.gabrielaangebrandt.funworld.forgotten_password_activity.ForgottenPasswordContract;
import com.gabrielaangebrandt.funworld.models.data_model.Player;
import com.gabrielaangebrandt.funworld.models.database.DatabaseManager;

/**
 * Created by Plava tvornica on 17.8.2017..
 */

public class ForgottenPasswordImpl implements ForgottenPasswordContract.ForgottenPasswordPresenter {
    ForgottenPasswordContract.ForgottenPasswordView view;

    public ForgottenPasswordImpl(ForgottenPasswordContract.ForgottenPasswordView view) {
        this.view = view;
    }

    @Override
    public String checkValues(String databaseColumn, String value, String selectedItem, String answer) {
        Player player = DatabaseManager.getPlayer(databaseColumn, value);
        if (player != null) {
            if (player.getQuestion().equals(selectedItem)) {
                if (player.getAnswer().equals(answer)) {
                    return player.getPassword();
                }
            }
        }
        return null;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {

    }
}
