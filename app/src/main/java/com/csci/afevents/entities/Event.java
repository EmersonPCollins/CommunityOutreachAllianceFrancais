package com.csci.afevents.entities;

import java.util.Objects;

public class Event {
    private String eventId;
    private String eventName;
    private String description;
    private String imageUrl;
    private int date;

    public Event(String eventId, String eventName, String description, int date, String imageUrl) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.date = date;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return date == event.date &&
                Objects.equals(eventId, event.eventId) &&
                Objects.equals(eventName, event.eventName) &&
                Objects.equals(description, event.description) &&
                Objects.equals(imageUrl, event.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, description, imageUrl, date);
    }
}
