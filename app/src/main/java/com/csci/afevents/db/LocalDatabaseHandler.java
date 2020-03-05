package com.csci.afevents.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.csci.afevents.entities.Event;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class LocalDatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AFH";
    public static final String TABLE_NAME = "event_table";
    public static final String EVENT_NAME = "name";
    public static final String EVENT_DESC = "description";
    public static final String EVENT_DATE = "date";
    public static final String EVENT_IMAGE = "image_url";
    public static final String EVENT_LONG = "Longitude";
    public static final String EVENT_LAT = "Latitude";

    public LocalDatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY, name VARCHAR(255), description TEXT, date INTEGER, image_url TEXT, Longitude REAL, Latitude REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_NAME, event.getEventName());
        contentValues.put(EVENT_DESC, event.getDescription());
        contentValues.put(EVENT_DATE, event.getDate());
        contentValues.put(EVENT_IMAGE, event.getImageUrl());
        contentValues.put(EVENT_LONG, event.getLongitude());
        contentValues.put(EVENT_LAT, event.getLatitude());
        long result = db.insert(TABLE_NAME,null, contentValues);
        return result != -1;
    }

    public List<Event> getAllEvents(){
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_NAME,null);
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getDouble(5),
                        cursor.getDouble(6)
                );
                events.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }
}
