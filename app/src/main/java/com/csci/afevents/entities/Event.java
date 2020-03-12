package com.csci.afevents.entities;

import java.io.Serializable;
import java.util.Objects;

public class Event implements Serializable {
    private String eventId;
    private String eventName;
    private String description;
    private String imageUrl;
    private int date;
    private double latitude;
    private double longitude;

    public Event(String eventId, String eventName, String description, int date, String imageUrl,
                 double longitude, double latitude) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.date = date;
        this.imageUrl = imageUrl;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public int getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
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
                Objects.equals(imageUrl, event.imageUrl) &&
                Objects.equals(longitude, event.longitude) &&
                Objects.equals(latitude, event.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, description, imageUrl, date, longitude, latitude);
    }
}
