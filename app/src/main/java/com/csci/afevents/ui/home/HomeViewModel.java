package com.csci.afevents.ui.home;

import android.content.Context;

import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.api.EventRetrieverFactory;
import com.csci.afevents.entities.Event;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private LiveData<List<Event>> events;
    private EventRetriever retriever;

    public HomeViewModel(Context context) {
        events = new MutableLiveData<>();
        retriever = EventRetrieverFactory.getInstance(context);
        events = retriever.getEvents();
    }

    public LiveData<List<Event>> getEvents() {
        return events;
    }
}