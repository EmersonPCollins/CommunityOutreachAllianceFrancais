package com.csci.afevents.impl;

import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.entities.Event;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DummyEventRetriever implements EventRetriever {

    @Override
    public LiveData<List<Event>> getEvents() {
        MutableLiveData<List<Event>> data = new MutableLiveData<>();
        List<Event> list = new ArrayList<>();
        Event event = new Event("1", "first event", "omellete du fromage", "2020-02-26", "https://www.holland.com/upload_mm/9/c/1/65174_fullimage_joods-bruidje.jpg", 1.1, 2.2, "24 fake st", "cheese omellete", "", "");
        list.add(event);
        data.setValue(list);
        return data;
    }
}
