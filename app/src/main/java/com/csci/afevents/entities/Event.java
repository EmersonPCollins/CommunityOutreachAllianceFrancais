package com.csci.afevents.entities;

public class Event {
    private String eventId;
    private String eventName;
    private String description;
    private long date;

    public Event(String eventId, String eventName, String description, long date) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.date = date;
    }
    public String getEventId(){
        return eventId;
    }
    public String getEventName(){
        return eventName;
    }
    public String getDescription(){
        return description;
    }
    public long getDate(){
        return date;
    }
}
