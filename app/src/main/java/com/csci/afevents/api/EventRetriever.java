package com.csci.afevents.api;

import com.csci.afevents.entities.Event;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface EventRetriever {
    public LiveData<List<Event>> getEvents();

}

