package com.praktek.sportsdb2491.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.praktek.sportsdb2491.model.Events;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_sports";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "table_sports";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_EVENTS = "events";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_STATUS = "status";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_IMAGE + " TEXT," +
                        COLUMN_EVENTS + " TEXT," +
                        COLUMN_DATE + " DATE," +
                        COLUMN_STATUS + " TEXT" +
                        ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public ArrayList<Events> getAllEvents() {
        ArrayList<Events> listEvents = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME,new String[]{COLUMN_ID,COLUMN_IMAGE,COLUMN_EVENTS,COLUMN_DATE,COLUMN_STATUS}, null,null,null,null,null);

        if (c != null && c.moveToFirst()) {
            do {
                Events events = new Events();

                events.setIdEvent(c.getString(0));
                events.setStrThumb(c.getString(1));
                events.setStrEvent(c.getString(2));
                events.setDateEvent(c.getString(3));
                events.setStrStatus(c.getString(4));

                listEvents.add(events);
            } while (c.moveToNext());
        }
        db.close();
        return listEvents;
    }

    public void addEvents(Events events) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_IMAGE,events.getStrThumb());
        values.put(COLUMN_EVENTS,events.getStrEvent());
        values.put(COLUMN_DATE,events.getDateEvent());
        values.put(COLUMN_STATUS,events.getStrStatus());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void deleteEvents(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "id = " + id, null);
        db.close();
    }
}
