package com.csci.afevents.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Event implements Serializable {
    private String eventId;
    private String eventName;
    private String frenchDescription;
    private String englishDescription;
    private String imageUrl;
    private String date;
    private String address;
    private double latitude;
    private double longitude;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private static DateFormat monthFormat = new SimpleDateFormat("MMM", Locale.US);
    private static DateFormat dayFormat = new SimpleDateFormat("dd", Locale.US);

    public Event(String eventId, String eventName, String frenchDescription, String date, String imageUrl,
                 double longitude, double latitude, String address, String englishDescription) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.frenchDescription = frenchDescription;
        this.englishDescription = englishDescription;
        this.date = date;
        this.imageUrl = imageUrl;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getFrenchDescription() {
        return frenchDescription;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public String getMonth() {
        try {
            Date parsed = format.parse(date);
            return monthFormat.format(parsed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String getDay() {
        try {
            Date parsed = format.parse(date);
            return dayFormat.format(parsed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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
        return getEventId().equals(event.getEventId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, frenchDescription, imageUrl, date, longitude, latitude);
    }

}
