package com.gungalovelectronics.budget_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constants.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
        onCreate(db);
    }

    //insert info function

    public long insertInfo(String income, String expense, String budget, String timestamp){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.COL_INCOME, income);
        values.put(Constants.COL_EXPENSE, expense);
        values.put(Constants.COL_TIMESTAMP, timestamp);
        values.put(Constants.COL_BUDGET, budget);

        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return id;
    }
}
