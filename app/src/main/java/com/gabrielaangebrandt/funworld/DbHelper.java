/*
package com.gabrielaangebrandt.funworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gabrielaangebrandt.funworld.models.data_model.Player;



public class DbHelper extends SQLiteOpenHelper {
    private static DbHelper dbHelper = null;
    DbHelper(Context context) {
        super(context.getApplicationContext(),Schema.DATABASE_NAME,null,Schema.SCHEMA_VERSION);
    }
    public static synchronized DbHelper getInstance(Context context){
        if(dbHelper == null){
            dbHelper = new DbHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PLAYERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE_PLAYERS);
        this.onCreate(sqLiteDatabase);
    }

    static final String CREATE_TABLE_PLAYERS = "CREATE TABLE " + Schema.TABLE_PLAYERS + " (" + Schema.NAME + " TEXT," + Schema.USERNAME + " TEXT," + Schema.PASSWORD + " TEXT," + Schema.EMAIL + " TEXT,"
            + Schema.QUESTION + " TEXT," + Schema.ANSWER + " TEXT);";

    static final String DROP_TABLE_PLAYERS = "DROP TABLE IF EXISTS " + Schema.TABLE_PLAYERS;

    public void insertData(Player player){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.NAME, player.getName());
        contentValues.put(Schema.USERNAME, player.getUsername());
        contentValues.put(Schema.PASSWORD, player.getPassword());
        contentValues.put(Schema.EMAIL, player.getEmail());
        contentValues.put(Schema.QUESTION, player.getQuestion());
        contentValues.put(Schema.ANSWER, player.getAnswer());
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        writeableDatabase.insert(Schema.TABLE_PLAYERS, Schema.USERNAME,contentValues);
        writeableDatabase.close();
    }

    public static class Schema{
        private static final int SCHEMA_VERSION = 1;
        private static final String DATABASE_NAME = "funWorld.db";
        static final String TABLE_PLAYERS = "players";
        static final String NAME = "name";
        static final String USERNAME = "username";
        static final String PASSWORD = "password";
        static final String EMAIL = "email";
        static final String QUESTION = "question";
        static final String ANSWER = "answer";
    }
}
*/
