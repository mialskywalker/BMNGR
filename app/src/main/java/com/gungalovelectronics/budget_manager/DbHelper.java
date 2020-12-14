package com.gungalovelectronics.budget_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {


    public static final String BUDGET_TABLE = "BUDGET_TABLE";
    public static final String COLUMN_INCOME = "INCOME";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_ID = "ID";

    public DbHelper(@Nullable Context context) {
        super(context, "budget.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + BUDGET_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_INCOME + " DOUBLE, " + COLUMN_DATE + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BUDGET_TABLE);
        onCreate(db);
    }


    public boolean addOne(BudgetModel budgetModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_INCOME, budgetModel.getIncome());
        cv.put(COLUMN_DATE, budgetModel.getDate());

        long insert = db.insert(BUDGET_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return  true;
        }

    }

    //
    public Integer deleteOne(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(BUDGET_TABLE,"COLUMN_ID = ?",new String [] {id});
    }

}
