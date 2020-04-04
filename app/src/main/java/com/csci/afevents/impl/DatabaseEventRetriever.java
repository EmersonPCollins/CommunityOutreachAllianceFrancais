package com.csci.afevents.impl;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.db.LocalDatabaseHandler;
import com.csci.afevents.entities.Event;

import java.util.List;

public class DatabaseEventRetriever implements EventRetriever {

    private LocalDatabaseHandler db;

    public DatabaseEventRetriever(Context context) {
        db = new LocalDatabaseHandler(context);
    }

    @Override
    public LiveData<List<Event>> getEvents() {
        MutableLiveData<List<Event>> data = new MutableLiveData<>();
        data.setValue(db.getAllEvents());
        return data;
    }

}
