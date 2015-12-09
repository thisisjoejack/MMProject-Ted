package com.money.mmproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TransactionsDB extends SQLiteOpenHelper {

        private static String DBNAME = "transactiondb";
        private static int VERSION = 1;
        public static final String KEY_ID = "_id";
        public static final String FIELD_DATE = "transdate";
        public static final String FIELD_AMOUNT = "amount";
        public static final String FIELD_CATEGORY = "category";
        public static final String FIELD_DESCRIPTION = "description";
        private static final String DATABASE_TABLE = "transactions";

        private SQLiteDatabase mDB;

        public TransactionsDB(Context context) {
            super(context, DBNAME, null, VERSION);
            this.mDB = getWritableDatabase();
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql =     "create table " + DATABASE_TABLE + " ( " +
                    KEY_ID + " integer primary key autoincrement , " +
                    FIELD_DATE + " integer , " +
                    FIELD_AMOUNT + " double , " +
                    FIELD_CATEGORY + " text , " +
                    FIELD_DESCRIPTION + " text " +
                    " ) ";
            db.execSQL(sql);
        }

        public long insert(ContentValues contentValues){
            long rowID = mDB.insert(DATABASE_TABLE, null, contentValues);
            System.out.println("HIER BIN ICH");
            Cursor c=mDB.rawQuery("SELECT * FROM "+DATABASE_TABLE,null);
            DatabaseUtils dbu = new DatabaseUtils();
            DatabaseUtils.dumpCursor(c);

            return rowID;
        }

        public int del(){
            int cnt = mDB.delete(DATABASE_TABLE, null, null);
            return cnt;
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        public Cursor getInformation(TransactionsDB updb) {
            SQLiteDatabase readDB = updb.getReadableDatabase();
            String[] columns = { FIELD_DATE,FIELD_AMOUNT,FIELD_CATEGORY,FIELD_DESCRIPTION};
            Cursor CR = readDB.query(DATABASE_TABLE, columns, null,null,null,null,null);
            return CR;
        }

        public Cursor getInformationById(TransactionsDB updb, int id) {
            SQLiteDatabase readDB = updb.getReadableDatabase();
            String[] columns = { FIELD_DATE,FIELD_AMOUNT,FIELD_CATEGORY,FIELD_DESCRIPTION};
            String where = KEY_ID+"="+id;
            Cursor CR = readDB.query(true, DATABASE_TABLE,columns, where, null,null,null,null,null);
            return CR;
        }

        public void delete_byID(int id){
            mDB.delete(DATABASE_TABLE, KEY_ID + "=" + id, null);
        }

}
