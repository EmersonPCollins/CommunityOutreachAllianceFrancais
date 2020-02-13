package com.csci.afevents.entities;

public class Event {
    private String eventId;
    private String eventName;
    private String description;
    private String imageUrl;
    private long date;

    public Event(String eventId, String eventName, String description, String imageUrl, long date) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.date = date;
    }
}
