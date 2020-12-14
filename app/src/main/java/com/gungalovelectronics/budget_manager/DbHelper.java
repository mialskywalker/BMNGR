package com.gungalovelectronics.budget_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "budget.db";
    public static final String TABLE_NAME = "budget_table";
    public static final String COL_ID = "ID";
    public static final String COL_INCOME = "INCOME";
    public static final String COL_EXPENSE = "EXPENSE";
    public static final String COL_BUDGET = "BUDGET";
    public static final String COL_TIMESTAMP = "DATE";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, INCOME INTEGER, EXPENSE INTEGER, BUDGET INTEGER, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String income, String expense, String budget, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_INCOME, income);
        contentValues.put(COL_EXPENSE, expense);
        contentValues.put(COL_BUDGET, budget);
        contentValues.put(COL_TIMESTAMP, date);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}
