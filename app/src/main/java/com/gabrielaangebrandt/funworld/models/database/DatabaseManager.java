package com.gabrielaangebrandt.funworld.models.database;

import com.gabrielaangebrandt.funworld.base.Converter;
import com.gabrielaangebrandt.funworld.base.SharedPrefs;
import com.gabrielaangebrandt.funworld.models.data_model.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

/**
 * Created by Plava tvornica on 17.8.2017..
 */

public class DatabaseManager {
    public static String setMemoryHighscore(String databaseElement, String value, Date score){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Realm realm = DatabaseConfig.getRealmInstance();
        realm.beginTransaction();
        Player user = realm.where(Player.class).equalTo(databaseElement, value).findFirst();
        if (user != null) {
            try {
                if (score.before(simpleDateFormat.parse(user.getHsMemory()))) {
                    user.setHsMemory(simpleDateFormat.format(score));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
        return user.getHsMemory();
    }
    public static int setTiltHighscore(String databaseElement, String username, int counterTrue){
        Realm realm = DatabaseConfig.getRealmInstance();
        realm.beginTransaction();
        Player user = realm.where(Player.class).equalTo(databaseElement, username).findFirst();
        if (user != null) {
            if (user.getHsTilt() < counterTrue) {
                user.setHsTilt(counterTrue);
            }
        }
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
        return user.getHsTilt();

    }
    public static int setPicadoHighscore(String databaseElement, String value, int score) {
        Realm realm = DatabaseConfig.getRealmInstance();
        realm.beginTransaction();
        Player user = realm.where(Player.class).equalTo(databaseElement, value).findFirst();
        if (user != null) {
            if (user.getHsPicado() >  score) {
                user.setHsPicado( score);
            }
        }
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
        return user.getHsPicado();
    }
}
