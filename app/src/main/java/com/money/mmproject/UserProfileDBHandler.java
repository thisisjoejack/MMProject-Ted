package com.money.mmproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserProfileDBHandler extends SQLiteOpenHelper {
    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "UserProfile";

    //Labels table name
    private static final String TABLE_NAME = "Profile";

    //Label Columns
    private static final String KEY_ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String INCOME = "income";
    public static final String SAVING = "saving";
    public static final String SPENDING = "spending";

    private SQLiteDatabase db;

    public UserProfileDBHandler (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Category table create query
        String sql = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_ID + "INTEGER PRIMARY KEY," +
                FIRST_NAME + " TEXT , " +
                LAST_NAME + " TEXT , " +
                INCOME + " DOUBLE , " +
                SAVING + " DOUBLE , " +
                SPENDING + " DOUBLE " + ")";
        db.execSQL(sql);
    }

    public void insert(String first, String last, double income, double saving, double spending) {

        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // set contents in ContentValues
        contentValues.put(UserProfileDBHandler.FIRST_NAME, first);
        contentValues.put(UserProfileDBHandler.LAST_NAME, last);
        contentValues.put(UserProfileDBHandler.INCOME,income);
        contentValues.put(UserProfileDBHandler.SAVING,saving);
        contentValues.put(UserProfileDBHandler.SPENDING, spending);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }


    public void update(UserProfileDBHandler updb,String first, String last,
                       double income, double saving, double spending) {
        SQLiteDatabase writeDB = updb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // set contents in ContentValues
        contentValues.put(UserProfileDBHandler.FIRST_NAME, first);
        contentValues.put(UserProfileDBHandler.LAST_NAME, last);
        contentValues.put(UserProfileDBHandler.INCOME,income);
        contentValues.put(UserProfileDBHandler.SAVING,saving);
        contentValues.put(UserProfileDBHandler.SPENDING, spending);

        writeDB.update(TABLE_NAME, contentValues, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getInformation(UserProfileDBHandler updb) {
        SQLiteDatabase readDB = updb.getReadableDatabase();
        String[] columns = {FIRST_NAME,LAST_NAME,INCOME,SAVING,SPENDING};
        Cursor CR = readDB.query(TABLE_NAME, columns, null,null,null,null,null);
        return CR;
    }

}