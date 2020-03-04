package com.csci.afevents.impl;

import android.content.Context;

import androidx.annotation.Nullable;

import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.db.LocalDatabaseHandler;
import com.csci.afevents.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class DatabaseEventRetirever implements EventRetriever {

    private LocalDatabaseHandler db;

    public DatabaseEventRetirever (Context context) {
        db = new LocalDatabaseHandler(context);
    }

    @Override
    public List<Event> getEvents() {
        return db.getAllEvents();
    }

}
