package com.csci.afevents.api;

import com.csci.afevents.entities.Event;

import java.util.List;

public interface EventRetriever {
    public List<Event> getEvents();
}
