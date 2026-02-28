package com.example.codefesttest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context c) {
        super(c, "cafeDB", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table accounts(userID integer primary key autoincrement, username text, password text)");
        db.execSQL("create table products(prodID integer primary key autoincrement, prodName text, price int, prodDescription text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVer, int oldVer) {
        //wala pa pero needed to para di magerror
    }

    public int executeScalar(SQLiteDatabase db, String query, String[] arguments) {
        int result = 0;

        try {
            Cursor cursor = db.rawQuery(query, arguments);
            if (cursor.moveToFirst()) {
                result = cursor.getInt(0);
            }
        } catch (Exception e) {
            Log.d( "db error", e.getMessage());
        }
        return result;
    }

    public int executeNonQuery(SQLiteDatabase db, String query, Object[] arguments) {
        int result = 0;
        try {
            db.execSQL(query, arguments);
            result = 1;
        } catch (Exception e) {
            Log.d("db error", e.getMessage());
        }
        return result;
    }

    public void content(SQLiteDatabase db){
        executeNonQuery(db, "insert into accounts values (?, ?, ?)", new Object[]{1, "test", "123"});
        executeNonQuery(db, "insert into products values (?, ?, ?, ?)", new Object[]{1, "testProd", 999, "dasdasldnaslknd"});
        executeNonQuery(db, "insert into products values (?, ?, ?, ?)", new Object[]{2, "the true product", 999, "dasdasldnaslknd"});
    }
}
