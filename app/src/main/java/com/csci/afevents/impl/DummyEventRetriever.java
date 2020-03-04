package com.csci.afevents.impl;

import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class DummyEventRetriever implements EventRetriever {

    @Override
    public List<Event> getEvents() {
        List<Event> list= new ArrayList<>();
        list.add(new Event("#1","Wine Tasting","Come drink some wine and eat cheese",20200217, "https://www.holland.com/upload_mm/9/c/1/65174_fullimage_joods-bruidje.jpg"));
        list.add(new Event("#2","Home-coming","Coming back home",20200221, "https://www.holland.com/upload_mm/9/c/1/65174_fullimage_joods-bruidje.jpg"));
        list.add(new Event("#3","First day of School","Come drink some wine and eat cheese",20200217, "https://i85.servimg.com/u/f85/12/05/06/84/affbla10.jpg"));
        list.add(new Event("#4","Event 4","Come drink some wine and eat cheese",20200312, "https://www.holland.com/upload_mm/9/c/1/65174_fullimage_joods-bruidje.jpg"));
        list.add(new Event("#5","Event 5","Come drink some wine and eat cheese",20200417, "https://www.holland.com/upload_mm/9/c/1/65174_fullimage_joods-bruidje.jpg"));
        return list;
    }
}
