package com.gabrielaangebrandt.funworld.models.database;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DatabaseConfig extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration defaultConfig = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(defaultConfig);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }
}
