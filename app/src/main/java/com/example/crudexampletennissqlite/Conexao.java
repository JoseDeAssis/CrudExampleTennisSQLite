package com.example.crudexampletennissqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao  extends SQLiteOpenHelper {

    public Conexao(@Nullable Context context) {
        super(context, "tennis.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CUSTOMER_TABLE ( CUSTOMER_TENNIS_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CUSTOMER_TENNIS_NAME TEXT,  CUSTOMER_TENNIS_PRICE REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE " + TENNIS_TABLE);
//        onCreate(sqLiteDatabase);
    }
}
