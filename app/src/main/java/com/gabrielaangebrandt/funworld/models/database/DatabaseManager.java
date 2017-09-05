package com.gabrielaangebrandt.funworld.models.database;

import com.gabrielaangebrandt.funworld.base.Converter;
import com.gabrielaangebrandt.funworld.models.data_model.Player;

import io.realm.Realm;

/**
 * Created by Plava tvornica on 17.8.2017..
 */

public class DatabaseManager {
    public static long setMemoryHighscore(String databaseElement, String value, long score){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Player user = realm.where(Player.class).equalTo(databaseElement, value).findFirst();
        if (user != null) {
            if (user.getHsMemory() > score) {
                user.setHsMemory(score);
            }
            Converter.getLongtoTime(user.getHsMemory());
        }
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
        return user.getHsMemory();
    }




}
