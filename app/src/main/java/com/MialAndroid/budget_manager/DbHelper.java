package com.MialAndroid.budget_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String BUDGET_TABLE = "BUDGET_TABLE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_INCOME = "INCOME";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_ID = "ID";

     DbHelper(@Nullable Context context) {
        super(context, "budget.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + BUDGET_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_INCOME + " DOUBLE, " + COLUMN_DATE + " TEXT)";

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

        cv.put(COLUMN_DESCRIPTION, budgetModel.getDescription());
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

    Cursor readAllData(){
        String query = "SELECT * FROM " + BUDGET_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
            return cursor;
    }

    //need fixing
   /* void updateData(String row_id, double income, String date, String description){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();

         cv.put(COLUMN_INCOME, income);
         cv.put(COLUMN_DATE, date);

         long result = db.update(BUDGET_TABLE, cv, "ID=?",new String[] {row_id});
         if(result == -1){
             Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
         }
         else{
             Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
         }
    } */

//    void updateData(double newValue, String id){
//         SQLiteDatabase db = this.getWritableDatabase();
//         String query = "UPDATE " + BUDGET_TABLE + " SET " + COLUMN_INCOME + " = " + newValue + " WHERE " + COLUMN_ID + " = " + id + ";";
//         db.execSQL(query);
//    }

    void deleteOneRow(String row_id){
         SQLiteDatabase db = this.getWritableDatabase();
         long result = db.delete(BUDGET_TABLE, "ID=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully deleted!", Toast.LENGTH_SHORT).show();
        }
    }


}
