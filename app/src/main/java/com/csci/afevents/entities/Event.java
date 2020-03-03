package com.csci.afevents.entities;

public class Event {
    private String eventId;
    private String eventName;
    private String description;
    private int date;

    public Event(String eventId, String eventName, String description, int date) {
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
    public int getDate(){
        return date;
    }
}
