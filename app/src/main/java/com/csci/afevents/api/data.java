package com.csci.afevents.api;

import com.csci.afevents.R;
import com.csci.afevents.entities.Event;
import com.csci.afevents.impl.DummyEventRetriever;

import java.util.List;

/**
 * @author Adama Camara
 *
 * A static class that will help with binding data
 * by parsing Event' data into String
 * then stored them in static method.
 *
 * Called in ListAdapter.java
 */

public class data {

    public static List<Event> events = new DummyEventRetriever().getEvents();
    public static String[] name = loadTitle();
    public static String[] description = loadDescription();
    public static long[] date = loadDate();


    public static String[] loadTitle(){
        String [] title = new String [events.size()];

        for(int i=0; i<events.size();i++){
            Event e = events.get(i);
            title[i]=e.getEventName();
        }

        return title;
    }
    public static String[] loadDescription(){
        String [] title = new String [events.size()];

        for(int i=0; i<events.size();i++){
            Event e = events.get(i);
            title[i]=e.getDescription();
        }

        return title;
    }
    public static long[] loadDate(){
        long [] title = new long[events.size()];

        for(int i=0; i<events.size();i++){
            Event e = events.get(i);
            title[i]=e.getDate();
        }

        return title;
    }
}
