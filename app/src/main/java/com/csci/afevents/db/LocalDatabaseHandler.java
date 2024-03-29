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

    public static final String TABLE_NAME = "event_table";
    private static final String DATABASE_NAME = "AFH";
    private static final String EVENT_NAME = "name";
    private static final String EVENT_DESC_FR = "description_fr";
    private static final String EVENT_DESC_EN = "description_en";
    private static final String EVENT_ADDRESS = "address";
    private static final String EVENT_DATE = "date";
    private static final String EVENT_IMAGE = "image_url";
    private static final String EVENT_LONG = "longitude";
    private static final String EVENT_LAT = "latitude";
    private static final String EVENT_COST_FR = "cost_fr";
    private static final String EVENT_COST_EN = "cost_en";

    public LocalDatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY," +
                " name VARCHAR(255), " +
                "description_fr TEXT, " +
                "description_en TEXT, " +
                "address TEXT, " +
                "date TEXT, " +
                "image_url TEXT, " +
                "longitude REAL, " +
                "latitude REAL, " +
                "cost_fr TEXT," +
                "cost_en TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_NAME, event.getEventName());
        contentValues.put(EVENT_DESC_FR, event.getFrenchDescription());
        contentValues.put(EVENT_DESC_EN, event.getEnglishDescription());
        contentValues.put(EVENT_ADDRESS, event.getAddress());
        contentValues.put(EVENT_DATE, event.getDate());
        contentValues.put(EVENT_IMAGE, event.getImageUrl());
        contentValues.put(EVENT_LONG, event.getLongitude());
        contentValues.put(EVENT_LAT, event.getLatitude());
        contentValues.put(EVENT_COST_FR, event.getCostFr());
        contentValues.put(EVENT_COST_EN, event.getCostEn());
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getDouble(7),
                        cursor.getDouble(8),
                        cursor.getString(4),
                        cursor.getString(3),
                        cursor.getString(9),
                        cursor.getString(10));
                events.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }

    public void dropAndSetEvents(List<Event> events) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        for (Event event : events) {
            insertEvent(event);
        }
    }
}
