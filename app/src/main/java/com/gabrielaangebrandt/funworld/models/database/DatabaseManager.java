package com.gabrielaangebrandt.funworld.models.database;

import com.gabrielaangebrandt.funworld.models.data_model.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;


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

    public static String checkQuestion(String databaseElement, String value){
        Realm realm = DatabaseConfig.getRealmInstance();
        Player user = realm.where(Player.class).equalTo(databaseElement,value).findFirst();
        if(user != null){
            return user.getQuestion();
        }
        return null;
    }

    public static String checkAnswer(String databaseElement, String value){
        Realm realm = DatabaseConfig.getRealmInstance();
        Player user = realm.where(Player.class).equalTo(databaseElement,value).findFirst();
        if(user != null){
            return user.getAnswer();
        }
        return null;
    }

    public static String getPass(String databaseElement, String value){
        Realm realm = DatabaseConfig.getRealmInstance();
        Player user = realm.where(Player.class).equalTo(databaseElement,value).findFirst();
        if(user != null){
            return user.getPassword();
        }
        return null;
    }

    public static Player loginCheck(String databaseElement1, String value1, String databaseElement2, String value2){
        Realm realm = DatabaseConfig.getRealmInstance();
        return realm.where(Player.class).equalTo(databaseElement1,value1).equalTo(databaseElement2,value2).findFirst();
    }

    public static Player checkIfUserExists(String databaseElement, String value){
        Realm realm = DatabaseConfig.getRealmInstance();
        Player user = realm.where(Player.class).equalTo(databaseElement, value).findFirst();
        return user;
    }

    public static void savePlayer(Player player) {
        Realm object = DatabaseConfig.getRealmInstance();
        object.beginTransaction();
        object.copyToRealmOrUpdate(player);
        object.commitTransaction();
    }

}
