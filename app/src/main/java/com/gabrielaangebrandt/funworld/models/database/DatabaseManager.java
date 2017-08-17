package com.gabrielaangebrandt.funworld.models.database;

import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.models.data_model.Player;

import io.realm.Realm;

/**
 * Created by Plava tvornica on 17.8.2017..
 */

public class DatabaseManager {
    public static Player getPlayer(String databaseElement, String value){
        Realm realm = Realm.getDefaultInstance();
        Player player = realm.where(Player.class).equalTo(databaseElement, value).findFirst();
        return player;
    }
}
