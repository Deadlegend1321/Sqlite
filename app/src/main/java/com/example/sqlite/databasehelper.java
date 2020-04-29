package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.jar.Attributes;

import static android.os.Build.ID;

public class databasehelper extends SQLiteOpenHelper {
    public static final String database_name = "Games.db";
    public static final String database_table = "Games_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "GENRE";


    public databasehelper(Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String t = "CREATE TABLE " + database_table + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT, " + COL_3 + " TEXT " + ")";
        db.execSQL(t);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + database_table);
        onCreate(db);

    }

    public boolean insertdata(String name, String genre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, name);
        cv.put(COL_3, genre);
        long result = db.insert(database_table, null, cv);
        if (result == -1) {
            return false;
        } else
            return true;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + database_table, null);
        return res;
    }
}
