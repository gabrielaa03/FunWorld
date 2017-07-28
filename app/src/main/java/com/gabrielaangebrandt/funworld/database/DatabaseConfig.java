package com.gabrielaangebrandt.funworld.database;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Gabriela on 27.7.2017..
 */

public class DatabaseConfig extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration defaultConfig = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(defaultConfig);
    }

    public static Realm getRealmInstance(){
        return Realm.getDefaultInstance();
    }
}
