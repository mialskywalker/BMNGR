package com.gungalovelectronics.budget_manager;

public class Constants {

    //db name
    public static final String DB_NAME = "budget_db";
    //db version
    public static final int DB_VERSION = 1;
    //db table
    public static final String TABLE_NAME = "budget_table";
    //db table columns
    public static final String COL_ID = "ID";
    public static final String COL_INCOME = "INCOME";
    public static final String COL_EXPENSE = "EXPENSE";
    public static final String COL_BUDGET = "BUDGET";
    public static final String COL_TIMESTAMP = "DATE";

    //create query for table
    public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_INCOME + " TEXT,"
            + COL_EXPENSE + " TEXT,"
            + COL_BUDGET + " TEXT,"
            + COL_TIMESTAMP + " TEXT"
            + ");";
}
